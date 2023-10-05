package com.fivengers.blooming.membership.adapter.out.persistence.mapper;

import com.fivengers.blooming.membership.adapter.out.persistence.entity.MembershipRecordJpaEntity;
import com.fivengers.blooming.membership.domain.MembershipRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MembershipRecordMapper {

    private final MembershipMapper membershipMapper;

    public MembershipRecord toDomain(MembershipRecordJpaEntity membershipRecordJpaEntity) {
        return MembershipRecord.builder()
                .id(membershipRecordJpaEntity.getId())
                .saleCount(membershipRecordJpaEntity.getSaleCount())
                .startDateOnWeek(membershipRecordJpaEntity.getStartDateOnWeek())
                .endDateOnWeek(membershipRecordJpaEntity.getEndDateOnWeek())
                .createdAt(membershipRecordJpaEntity.getCreatedAt())
                .modifiedAt(membershipRecordJpaEntity.getModifiedAt())
                .membership(membershipMapper.toDomain(
                        membershipRecordJpaEntity.getMembershipJpaEntity()))
                .build();
    }

    public MembershipRecordJpaEntity toJpaEntity(MembershipRecord membershipRecord) {
        return MembershipRecordJpaEntity.builder()
                .id(membershipRecord.getId())
                .saleCount(membershipRecord.getSaleCount())
                .startDateOnWeek(membershipRecord.getStartDateOnWeek())
                .endDateOnWeek(membershipRecord.getEndDateOnWeek())
                .membershipJpaEntity(membershipMapper.toJpaEntity(membershipRecord.getMembership()))
                .build();
    }

}
