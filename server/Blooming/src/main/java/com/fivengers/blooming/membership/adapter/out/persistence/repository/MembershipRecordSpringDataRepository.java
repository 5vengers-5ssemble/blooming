package com.fivengers.blooming.membership.adapter.out.persistence.repository;

import com.fivengers.blooming.membership.adapter.out.persistence.entity.MembershipJpaEntity;
import com.fivengers.blooming.membership.adapter.out.persistence.entity.MembershipRecordJpaEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRecordSpringDataRepository extends
        JpaRepository<MembershipRecordJpaEntity, Long> {

    Optional<MembershipRecordJpaEntity> findByStartDateOnWeekAndEndDateOnWeekAndMembershipJpaEntityId(
            LocalDateTime startDateOnWeek, LocalDateTime endDateOnWeek, Long membershipJpaEntityId);
}
