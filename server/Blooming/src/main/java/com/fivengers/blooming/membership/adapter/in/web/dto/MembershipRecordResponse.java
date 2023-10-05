package com.fivengers.blooming.membership.adapter.in.web.dto;

import com.fivengers.blooming.membership.domain.MembershipRecord;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record MembershipRecordResponse(Integer saleCount,
                                       LocalDateTime startDateOnWeek,
                                       LocalDateTime endDateOnWeek) {

    public static MembershipRecordResponse from(MembershipRecord membershipRecord) {
        return MembershipRecordResponse.builder()
                .saleCount(membershipRecord.getSaleCount())
                .startDateOnWeek(membershipRecord.getStartDateOnWeek())
                .endDateOnWeek(membershipRecord.getEndDateOnWeek())
                .build();
    }

}
