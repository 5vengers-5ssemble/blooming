package com.fivengers.blooming.membership.adapter.in.web.dto;

import com.fivengers.blooming.membership.domain.Membership;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MembershipListResponse(Long id,
                                     String title,
                                     String description,
                                     String thumbnailUri,
                                     LocalDateTime purchaseStart,
                                     LocalDateTime purchaseEnd,
                                     NftSaleResponse nftSale) {

    public static MembershipListResponse from(Membership membership) {
        return MembershipListResponse.builder()
                .id(membership.getId())
                .title(membership.getTitle())
                .description(membership.getDescription())
                .thumbnailUri(membership.getThumbnailUrl())
                .purchaseStart(membership.getPurchaseStart())
                .purchaseEnd(membership.getPurchaseEnd())
                .nftSale(NftSaleResponse.from(membership.getNftSale()))
                .build();
    }
}
