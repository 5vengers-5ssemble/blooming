package com.fivengers.blooming.membership.adapter.out.persistence.repository;

import static com.fivengers.blooming.membership.adapter.out.persistence.entity.QMembershipApplicationJpaEntity.membershipApplicationJpaEntity;

import com.fivengers.blooming.global.support.QuerydslRepositorySupport;
import com.fivengers.blooming.membership.adapter.out.persistence.entity.MembershipApplicationJpaEntity;
import com.fivengers.blooming.membership.domain.MembershipApplicationState;
import com.querydsl.core.types.dsl.BooleanExpression;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class MembershipApplicationQueryRepository extends QuerydslRepositorySupport {

    public MembershipApplicationQueryRepository() {
        super(MembershipApplicationJpaEntity.class);
    }

    public Page<MembershipApplicationJpaEntity> findByApplicationState(Pageable pageable,
            MembershipApplicationState state) {
        return applyPagination(pageable,
                query ->
                        query.selectFrom(membershipApplicationJpaEntity)
                                .leftJoin(membershipApplicationJpaEntity.artistJpaEntity)
                                .fetchJoin()
                                .where(equalState(state))
                                .offset(pageable.getOffset())
                                .limit(pageable.getPageSize()),
                countQuery ->
                        countQuery.select(membershipApplicationJpaEntity.count())
                                .from(membershipApplicationJpaEntity)
                                .where(equalState(state)));
    }

    private BooleanExpression equalState(MembershipApplicationState state) {
        return state == null ? null : membershipApplicationJpaEntity.applicationState.eq(state);
    }

    public Optional<MembershipApplicationJpaEntity> findById(Long applicationId) {
        return Optional.ofNullable(selectFrom(membershipApplicationJpaEntity)
                .where(membershipApplicationJpaEntity.id.eq(applicationId))
                .leftJoin(membershipApplicationJpaEntity.artistJpaEntity)
                .fetchJoin()
                .leftJoin(membershipApplicationJpaEntity.artistJpaEntity.memberJpaEntity)
                .fetchJoin()
                .fetchOne());
    }

    public Optional<MembershipApplicationJpaEntity> findByMemberIdAndApplicationState(Long memberId,
            MembershipApplicationState applicationState) {
        return Optional.ofNullable(selectFrom(membershipApplicationJpaEntity)
                .where(membershipApplicationJpaEntity.artistJpaEntity
                        .memberJpaEntity.id.eq(memberId)
                        .and(membershipApplicationJpaEntity.applicationState.eq(applicationState)))
                .leftJoin(membershipApplicationJpaEntity.artistJpaEntity)
                .fetchJoin()
                .leftJoin(membershipApplicationJpaEntity.artistJpaEntity.memberJpaEntity)
                .fetchJoin()
                .fetchOne());
    }
}
