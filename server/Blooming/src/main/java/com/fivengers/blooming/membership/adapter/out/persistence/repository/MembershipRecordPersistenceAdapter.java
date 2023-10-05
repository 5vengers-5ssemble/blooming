package com.fivengers.blooming.membership.adapter.out.persistence.repository;

import com.fivengers.blooming.global.exception.membership.MembershipRecordNotFoundException;
import com.fivengers.blooming.membership.adapter.out.persistence.entity.MembershipRecordJpaEntity;
import com.fivengers.blooming.membership.adapter.out.persistence.mapper.MembershipRecordMapper;
import com.fivengers.blooming.membership.application.port.out.MembershipRecordPort;
import com.fivengers.blooming.membership.domain.MembershipRecord;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MembershipRecordPersistenceAdapter implements MembershipRecordPort {

    private final MembershipRecordSpringDataRepository membershipRecordSpringDataRepository;
    private final MembershipRecordQueryRepository membershipRecordQueryRepository;
    private final MembershipRecordMapper membershipRecordMapper;

    @Override
    @Transactional
    public MembershipRecord save(MembershipRecord membershipRecord) {
        return membershipRecordMapper
                .toDomain(membershipRecordSpringDataRepository
                        .save(membershipRecordMapper.toJpaEntity(membershipRecord)));
    }

    @Override
    public List<MembershipRecord> findTopByMembershipIdOrderByStartDateDesc(Long membershipId,
            Long limit) {
        return membershipRecordQueryRepository
                .findByMembershipIdOrderByStartDateDesc(membershipId, limit).stream()
                .map(membershipRecordMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<MembershipRecord> findOnWeek(LocalDateTime startDate, LocalDateTime endDate,
            Long membershipId) {
        return membershipRecordSpringDataRepository
                .findByStartDateOnWeekAndEndDateOnWeekAndMembershipJpaEntityId(
                        startDate, endDate, membershipId)
                .map(membershipRecordMapper::toDomain);
    }

    @Override
    @Transactional
    public void update(MembershipRecord membershipRecord) {
        MembershipRecordJpaEntity record = membershipRecordSpringDataRepository
                .findById(membershipRecord.getId())
                .orElseThrow(MembershipRecordNotFoundException::new);

        record.update(membershipRecord);
    }
}
