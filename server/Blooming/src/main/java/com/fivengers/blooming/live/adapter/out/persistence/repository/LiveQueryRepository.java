package com.fivengers.blooming.live.adapter.out.persistence.repository;

import com.fivengers.blooming.artist.adapter.out.persistence.entity.QArtistJpaEntity;
import com.fivengers.blooming.global.support.QuerydslRepositorySupport;
import com.fivengers.blooming.live.adapter.out.persistence.entity.LiveJpaEntity;
import com.fivengers.blooming.live.adapter.out.persistence.entity.QLiveJpaEntity;
import com.fivengers.blooming.member.adapter.out.persistence.entity.QMemberJpaEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.function.Function;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class LiveQueryRepository extends QuerydslRepositorySupport {
    private final QMemberJpaEntity member = QMemberJpaEntity.memberJpaEntity;
    private final QArtistJpaEntity artist = QArtistJpaEntity.artistJpaEntity;
    private final QLiveJpaEntity live = QLiveJpaEntity.liveJpaEntity;

    public LiveQueryRepository(EntityManager em) {
        super(LiveJpaEntity.class);
    }

    Page<LiveJpaEntity> findActiveLive(Pageable pageable) {
        return applyPagination(
                pageable,
                this::findActiveLiveWithActiveMember,
                this::findActiveLiveCountWithActiveMember
        );
    }

    Page<LiveJpaEntity> findActiveLiveByTitleKeyword(String keyword, Pageable pageable) {
        Function<JPAQueryFactory, JPAQuery<LiveJpaEntity>> contentQuery =
                (query) -> findActiveLiveWithActiveMember(query)
                        .where(live.title.contains(keyword));

        Function<JPAQueryFactory, JPAQuery<Long>> countQuery =
                (query) -> findActiveLiveCountWithActiveMember(query)
                        .where(live.title.contains(keyword));

        return applyPagination(
                pageable, contentQuery, countQuery
        );
    }

    Page<LiveJpaEntity> findActiveLiveByArtist(String keyword, Pageable pageable) {

        Function<JPAQueryFactory, JPAQuery<LiveJpaEntity>> contentQuery =
                (query) -> findActiveLiveWithActiveMember(query)
                        .where(artist.stageName.contains(keyword));

        Function<JPAQueryFactory, JPAQuery<Long>> countQuery =
                (query) -> findActiveLiveCountWithActiveMember(query)
                        .where(artist.stageName.contains(keyword));

        return applyPagination(
                pageable, contentQuery, countQuery
        );
    }

    private JPAQuery<LiveJpaEntity> findActiveLiveWithActiveMember(JPAQueryFactory query) {
        return query.selectFrom(live)
                .leftJoin(live.artistJpaEntity, artist)
                .fetchJoin()
                .leftJoin(artist.memberJpaEntity, member)
                .fetchJoin()
                .where(isActiveArtist().and(isActiveLive()));
    }

    private JPAQuery<Long> findActiveLiveCountWithActiveMember(JPAQueryFactory query) {
        return query.select(live.count())
                .from(live)
                .leftJoin(live.artistJpaEntity, artist)
                .leftJoin(artist.memberJpaEntity, member)
                .where(isActiveArtist().and(isActiveLive()));
    }

    private BooleanExpression isActiveArtist() {
        return (member.deleted.isFalse()).and(artist.deleted.isFalse());
    }

    private BooleanExpression isActiveLive() {
        return live.endedAt.isNull();
    }

    public Long findActiveLiveIdByArtist(Long artistId) {
        return select(live.id)
                .from(live)
                .leftJoin(live.artistJpaEntity, artist)
                .leftJoin(artist.memberJpaEntity, member)
                .where(isActiveArtist().and(isActiveLive()).and(artist.id.eq(artistId)))
                .fetchOne();

    }
}
