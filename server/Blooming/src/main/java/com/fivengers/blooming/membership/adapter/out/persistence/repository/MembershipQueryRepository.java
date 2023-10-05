package com.fivengers.blooming.membership.adapter.out.persistence.repository;

import static com.fivengers.blooming.membership.adapter.out.persistence.entity.QMembershipJpaEntity.membershipJpaEntity;

import com.fivengers.blooming.global.support.QuerydslRepositorySupport;
import com.fivengers.blooming.membership.adapter.out.persistence.entity.MembershipJpaEntity;
import com.fivengers.blooming.membership.adapter.out.persistence.entity.QMembershipJpaEntity;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class MembershipQueryRepository extends QuerydslRepositorySupport {

    public MembershipQueryRepository() {
        super(MembershipJpaEntity.class);
    }

    public Optional<MembershipJpaEntity> findById(Long membershipId) {
        return Optional.ofNullable(selectFrom(membershipJpaEntity)
                .leftJoin(membershipJpaEntity.artistJpaEntity).fetchJoin()
                .leftJoin(membershipJpaEntity.artistJpaEntity.memberJpaEntity).fetchJoin()
                .where(membershipJpaEntity.id.eq(membershipId))
                .fetchOne());
    }

    public Page<MembershipJpaEntity> findLatestSeasonsGroupByArtist(Pageable pageable) {
        QMembershipJpaEntity sub = new QMembershipJpaEntity("sub");

        return applyPagination(pageable,
                latestSeasonsContentQuery(pageable, sub),
                latestSeasonsCountQuery());
    }

    public List<MembershipJpaEntity> findTopNSaleCount(long n) {
        return selectFetchJoinedMembership()
                .orderBy(membershipJpaEntity.saleCount.desc())
                .limit(n)
                .fetch();

    }

    private JPAQuery<MembershipJpaEntity> selectFetchJoinedMembership() {
        return selectFrom(membershipJpaEntity)
                .leftJoin(membershipJpaEntity.nftSaleJpaEntity).fetchJoin()
                .leftJoin(membershipJpaEntity.artistJpaEntity).fetchJoin()
                .leftJoin(membershipJpaEntity.artistJpaEntity.memberJpaEntity).fetchJoin();
    }

    public Page<MembershipJpaEntity> findByArtistNameLikeQuery(Pageable pageable,
            String searchQuery) {
        return applyPagination(pageable,
                query -> selectFetchJoinedMembership()
                        .where(membershipJpaEntity.artistJpaEntity.stageName.contains(searchQuery)),
                countQuery -> countQuery.select(membershipJpaEntity.count())
                        .where(membershipJpaEntity.artistJpaEntity.stageName.contains(searchQuery))
        );
    }

    private Function<JPAQueryFactory, JPAQuery<MembershipJpaEntity>> latestSeasonsContentQuery(
            Pageable pageable, QMembershipJpaEntity sub) {
        return query -> query.selectFrom(membershipJpaEntity)
                .leftJoin(membershipJpaEntity.nftSaleJpaEntity).fetchJoin()
                .leftJoin(membershipJpaEntity.artistJpaEntity).fetchJoin()
                .leftJoin(membershipJpaEntity.artistJpaEntity.memberJpaEntity).fetchJoin()
                .where(membershipJpaEntity.deleted.eq(false)
                        .and(membershipJpaEntity.artistJpaEntity.in(
                                select(sub.artistJpaEntity)
                                        .from(sub)
                                        .groupBy(sub.artistJpaEntity)
                                        .having(membershipJpaEntity.season.eq(sub.season.max())))))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
    }

    private Function<JPAQueryFactory, JPAQuery<Long>> latestSeasonsCountQuery() {
        return countQuery -> select(membershipJpaEntity.count())
                .from(membershipJpaEntity)
                .leftJoin(membershipJpaEntity.artistJpaEntity).fetchJoin()
                .leftJoin(membershipJpaEntity.artistJpaEntity.memberJpaEntity).fetchJoin();
    }

    public Page<MembershipJpaEntity> findByBetweenSeasonStartAndSeasonEnd(Pageable pageable,
            LocalDateTime now) {
        return applyPagination(pageable,
                query ->
                        query.selectFrom(membershipJpaEntity)
                                .leftJoin(membershipJpaEntity.nftSaleJpaEntity).fetchJoin()
                                .leftJoin(membershipJpaEntity.artistJpaEntity).fetchJoin()
                                .leftJoin(membershipJpaEntity.artistJpaEntity.memberJpaEntity)
                                .fetchJoin()
                                .where(membershipJpaEntity.seasonStart.before(now)
                                        .and(membershipJpaEntity.seasonEnd.after(now))),
                countQuery ->
                        countQuery.select(membershipJpaEntity.count())
                                .from(membershipJpaEntity)
                                .where(membershipJpaEntity.seasonStart.before(now)
                                        .and(membershipJpaEntity.seasonEnd.after(now))));
    }

    public Optional<MembershipJpaEntity> findByArtistIdAndBetweenSeasonStartAndSeasonEnd(
            Long artistId, LocalDateTime now) {
        return Optional.ofNullable(selectFrom(membershipJpaEntity)
                .leftJoin(membershipJpaEntity.nftSaleJpaEntity).fetchJoin()
                .leftJoin(membershipJpaEntity.artistJpaEntity).fetchJoin()
                .leftJoin(membershipJpaEntity.artistJpaEntity.memberJpaEntity).fetchJoin()
                .where(membershipJpaEntity.seasonStart.before(now)
                        .and(membershipJpaEntity.seasonEnd.after(now))
                        .and(membershipJpaEntity.artistJpaEntity.id.eq(artistId)))
                .fetchOne());
    }
}
