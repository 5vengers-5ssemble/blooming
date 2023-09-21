package com.fivengers.blooming.membership.adapter.in.web.dto;

import com.fivengers.blooming.artist.adapter.in.web.dto.ArtistOfMembershipResponse;
import com.fivengers.blooming.membership.domain.Membership;
import com.fivengers.blooming.nft.adapter.in.web.dto.NftDetailsResponse;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record MembershipDetailsResponse(String title,
                                        String description,
                                        Integer season,
                                        LocalDateTime seasonStart,
                                        LocalDateTime seasonEnd,
                                        LocalDateTime purchaseStart,
                                        LocalDateTime purchaseEnd,
                                        Integer saleCount,
                                        String thumbnailUri,
                                        LocalDateTime createdAt,
                                        LocalDateTime modifiedAt,
                                        ArtistOfMembershipResponse artist,
                                        NftSaleResponse nftSale) {

    public static MembershipDetailsResponse from(Membership membership) {
        return MembershipDetailsResponse.builder()
                .title(membership.getTitle())
                .description(membership.getDescription())
                .season(membership.getSeason())
                .seasonStart(membership.getSeasonStart())
                .seasonEnd(membership.getSeasonEnd())
                .purchaseStart(membership.getPurchaseStart())
                .purchaseEnd(membership.getPurchaseEnd())
                .saleCount(membership.getSaleCount())
                .thumbnailUri(membership.getThumbnailUrl())
                .createdAt(membership.getCreatedAt())
                .modifiedAt(membership.getModifiedAt())
                .artist(ArtistOfMembershipResponse.from(membership.getArtist()))
                .nftSale(NftSaleResponse.from(membership.getNftSale()))
                .build();
    }
}
