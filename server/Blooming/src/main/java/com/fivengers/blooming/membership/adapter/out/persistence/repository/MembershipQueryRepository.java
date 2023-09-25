package com.fivengers.blooming.membership.adapter.out.persistence.repository;

import com.fivengers.blooming.membership.adapter.out.persistence.entity.MembershipJpaEntity;
import com.fivengers.blooming.membership.adapter.out.persistence.entity.QMembershipJpaEntity;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.fivengers.blooming.membership.adapter.out.persistence.entity.QMembershipJpaEntity.membershipJpaEntity;
import static com.querydsl.jpa.JPAExpressions.select;

@Repository
public class MembershipQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final Querydsl querydsl;

    public MembershipQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
        JpaEntityInformation entityInformation = JpaEntityInformationSupport.getEntityInformation(
                MembershipJpaEntity.class, em);
        EntityPath path = SimpleEntityPathResolver.INSTANCE
                .createPath(entityInformation.getJavaType());
        querydsl = new Querydsl(em, new PathBuilder<>(path.getType(), path.getMetadata()));
    }

    Page<MembershipJpaEntity> findLatestSeasonsGroupByArtist(Pageable pageable) {
        QMembershipJpaEntity sub = new QMembershipJpaEntity("sub");

        JPAQuery<MembershipJpaEntity> query = queryFactory
                .selectFrom(membershipJpaEntity)
                .innerJoin(membershipJpaEntity.nftSaleJpaEntity).fetchJoin()
                .where(membershipJpaEntity.deleted.eq(false)
                        .and(membershipJpaEntity.artistJpaEntity.in(
                                select(sub.artistJpaEntity)
                                        .from(sub)
                                        .groupBy(sub.artistJpaEntity)
                                        .having(membershipJpaEntity.season.eq(sub.season.max())))))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<MembershipJpaEntity> memberships = querydsl.applySorting(pageable.getSort(), query)
                .fetch();

        Long count = queryFactory.select(membershipJpaEntity.count())
                .from(membershipJpaEntity)
                .fetchOne();

        return new PageImpl<>(memberships, pageable, count);
    }
}
