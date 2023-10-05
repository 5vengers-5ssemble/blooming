package com.fivengers.blooming.membership.adapter.out.persistence.repository;

import static com.fivengers.blooming.membership.adapter.out.persistence.entity.QMembershipRecordJpaEntity.membershipRecordJpaEntity;

import com.fivengers.blooming.global.support.QuerydslRepositorySupport;
import com.fivengers.blooming.membership.adapter.out.persistence.entity.MembershipRecordJpaEntity;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class MembershipRecordQueryRepository extends QuerydslRepositorySupport {

    public MembershipRecordQueryRepository() {
        super(MembershipRecordJpaEntity.class);
    }

    public List<MembershipRecordJpaEntity> findByMembershipIdOrderByStartDateDesc(Long membershipId,
            Long limit) {
        return selectFrom(membershipRecordJpaEntity)
                .where(membershipRecordJpaEntity.membershipJpaEntity.id.eq(membershipId))
                .orderBy(membershipRecordJpaEntity.startDateOnWeek.desc())
                .limit(limit)
                .fetch();
    }
}
