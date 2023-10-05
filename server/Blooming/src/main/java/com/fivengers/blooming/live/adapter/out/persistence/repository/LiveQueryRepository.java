package com.fivengers.blooming.live.adapter.out.persistence.repository;

import com.fivengers.blooming.artist.adapter.out.persistence.entity.QArtistJpaEntity;
import com.fivengers.blooming.global.support.QuerydslRepositorySupport;
import com.fivengers.blooming.live.adapter.out.persistence.entity.LiveJpaEntity;
import com.fivengers.blooming.live.adapter.out.persistence.entity.QLiveJpaEntity;
import com.fivengers.blooming.member.adapter.out.persistence.entity.QMemberJpaEntity;
import com.fivengers.blooming.nft.adapter.out.persistence.entity.QNftJpaEntity;
import com.fivengers.blooming.nft.adapter.out.persistence.entity.QNftOwnerInfoJpaEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class LiveQueryRepository extends QuerydslRepositorySupport {

    private final QMemberJpaEntity member = QMemberJpaEntity.memberJpaEntity;
    private final QArtistJpaEntity artist = QArtistJpaEntity.artistJpaEntity;
    private final QLiveJpaEntity live = QLiveJpaEntity.liveJpaEntity;
    private final QNftJpaEntity nft = QNftJpaEntity.nftJpaEntity;
    private final QNftOwnerInfoJpaEntity nftOwnerInfo = QNftOwnerInfoJpaEntity.nftOwnerInfoJpaEntity;

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

    Optional<LiveJpaEntity> findActiveLiveById(Long id) {
        return Optional.ofNullable(findActiveLiveWithActiveMember(getJpaQueryFactory())
                .where(live.id.eq(id))
                .fetchOne());
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

    public List<Long> findNftIdByMember(Long memberId) {
        return select(nftOwnerInfo.nftJpaEntity.id)
                .from(nftOwnerInfo)
                .where(nftOwnerInfo.owned.isTrue()
                        .and(nftOwnerInfo.memberJpaEntity.id.eq(memberId)))
                .fetch();
    }

    public List<LiveJpaEntity> findActiveLiveByNft(List<Long> nftIds) {
        return findActiveLiveWithActiveMember(getJpaQueryFactory())
                .where(live.artistJpaEntity.id.in(findArtistIdByNft(nftIds)))
                .fetch();
    }

    private JPQLQuery<Long> findArtistIdByNft(List<Long> nftIds) {
        return select(nft.artist.id)
                .from(nft)
                .where(nft.id.in(nftIds));
    }


}
