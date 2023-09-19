package com.fivengers.blooming.membership.adapter.in.web.dto;

import com.fivengers.blooming.membership.domain.Membership;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MembershipListResponse(String title,
                                     String description,
                                     String profileImg,
                                     LocalDateTime startDate,
                                     LocalDateTime endDate,
                                     Long totalProcess,
                                     Long nowProcess) {

    public static MembershipListResponse from(Membership membership) {
        return MembershipListResponse.builder()
                .title(membership.getTitle())
                .description(membership.getDescription())
                .profileImg(membership.getThumbnailUrl())
                .startDate(membership.getPurchaseStart())
                .endDate(membership.getPurchaseEnd())
                .totalProcess(membership.getNftSale().getTotalNftAmount())
                .nowProcess(membership.getNftSale().getSoldNftAmount())
                .build();
    }
}
