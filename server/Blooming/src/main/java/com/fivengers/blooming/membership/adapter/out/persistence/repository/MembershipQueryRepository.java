package com.fivengers.blooming.membership.adapter.out.persistence.repository;

import com.fivengers.blooming.membership.adapter.out.persistence.entity.MembershipJpaEntity;
import com.fivengers.blooming.membership.adapter.out.persistence.entity.QMembershipJpaEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.fivengers.blooming.membership.adapter.out.persistence.entity.QMembershipJpaEntity.membershipJpaEntity;
import static com.querydsl.jpa.JPAExpressions.select;

@Repository
public class MembershipQueryRepository {

    private final JPAQueryFactory queryFactory;

    public MembershipQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    Page<MembershipJpaEntity> findLatestSeasonsGroupByArtist(Pageable pageable) {
        QMembershipJpaEntity sub = new QMembershipJpaEntity("sub");

        List<MembershipJpaEntity> memberships = queryFactory
                .selectFrom(membershipJpaEntity)
                .innerJoin(membershipJpaEntity.nftSaleJpaEntity).fetchJoin()
                .where(membershipJpaEntity.deleted.eq(false)
                        .and(membershipJpaEntity.artistJpaEntity.in(
                                select(sub.artistJpaEntity)
                                        .from(sub)
                                        .groupBy(sub.artistJpaEntity)
                                        .having(membershipJpaEntity.season.eq(sub.season.max())))))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory.select(membershipJpaEntity.count())
                .from(membershipJpaEntity)
                .fetchOne();

        return new PageImpl<MembershipJpaEntity>(memberships, pageable, count);
    }
}
