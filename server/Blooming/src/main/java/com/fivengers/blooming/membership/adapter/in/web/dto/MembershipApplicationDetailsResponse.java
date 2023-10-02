package com.fivengers.blooming.membership.adapter.in.web.dto;

import com.fivengers.blooming.membership.domain.MembershipApplication;
import com.fivengers.blooming.membership.domain.MembershipApplicationState;
import com.fivengers.blooming.project.adapter.in.web.dto.ArtistResponse;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record MembershipApplicationDetailsResponse(String title,
                                                   String description,
                                                   LocalDateTime seasonStart,
                                                   LocalDateTime seasonEnd,
                                                   LocalDateTime purchaseStart,
                                                   LocalDateTime purchaseEnd,
                                                   String thumbnailUrl,
                                                   MembershipApplicationState applicationState,
                                                   LocalDateTime createdAt,
                                                   LocalDateTime modifiedAt,
                                                   ArtistResponse artist) {

    public static MembershipApplicationDetailsResponse from(
            MembershipApplication membershipApplication,
            ArtistResponse artistResponse) {
        return MembershipApplicationDetailsResponse.builder()
                .title(membershipApplication.getTitle())
                .description(membershipApplication.getDescription())
                .seasonStart(membershipApplication.getSeasonStart())
                .seasonEnd(membershipApplication.getSeasonEnd())
                .purchaseStart(membershipApplication.getPurchaseStart())
                .purchaseEnd(membershipApplication.getPurchaseEnd())
                .thumbnailUrl(membershipApplication.getThumbnailUrl())
                .applicationState(membershipApplication.getApplicationState())
                .createdAt(membershipApplication.getCreatedAt())
                .modifiedAt(membershipApplication.getModifiedAt())
                .artist(artistResponse)
                .build();
    }
}
