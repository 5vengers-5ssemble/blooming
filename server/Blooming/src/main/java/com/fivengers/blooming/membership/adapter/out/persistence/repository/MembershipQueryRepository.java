package com.fivengers.blooming.membership.adapter.out.persistence.repository;

import static com.fivengers.blooming.membership.adapter.out.persistence.entity.QMembershipJpaEntity.membershipJpaEntity;

import com.fivengers.blooming.global.support.QuerydslRepositorySupport;
import com.fivengers.blooming.membership.adapter.out.persistence.entity.MembershipJpaEntity;
import com.fivengers.blooming.membership.adapter.out.persistence.entity.QMembershipJpaEntity;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.function.Function;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class MembershipQueryRepository extends QuerydslRepositorySupport {

    public MembershipQueryRepository() {
        super(MembershipJpaEntity.class);
    }

    public Page<MembershipJpaEntity> findLatestSeasonsGroupByArtist(Pageable pageable) {
        QMembershipJpaEntity sub = new QMembershipJpaEntity("sub");

        return applyPagination(pageable,
                latestSeasonsContentQuery(pageable, sub),
                latestSeasonsCountQuery());
    }

    private Function<JPAQueryFactory, JPAQuery<MembershipJpaEntity>> latestSeasonsContentQuery(
            Pageable pageable, QMembershipJpaEntity sub) {
        return query -> query.selectFrom(membershipJpaEntity)
                .innerJoin(membershipJpaEntity.nftSaleJpaEntity).fetchJoin()
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
                .from(membershipJpaEntity);
    }
}
