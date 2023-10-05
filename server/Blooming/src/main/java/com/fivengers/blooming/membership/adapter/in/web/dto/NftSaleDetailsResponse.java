package com.fivengers.blooming.membership.adapter.in.web.dto;

import com.fivengers.blooming.membership.domain.Membership;
import com.fivengers.blooming.project.adapter.in.web.dto.ArtistResponse;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record NftSaleDetailsResponse (Long id,
                                      String title,
                                      String thumbnailUrl,
                                      String description,
                                      ArtistResponse artist,
                                      Integer saleCount,
                                      NftSaleResponse nftSale,
                                      Long salePrice,
                                      LocalDateTime purchaseStart,
                                      LocalDateTime purchaseEnd,
                                      String contractAddress) {

    public static NftSaleDetailsResponse from(Membership membership){
        return NftSaleDetailsResponse.builder()
                .id(membership.getId())
                .title(membership.getTitle())
                .thumbnailUrl(membership.getThumbnailUrl())
                .description(membership.getDescription())
                .artist(ArtistResponse.from(membership.getArtist()))
                .nftSale(NftSaleResponse.from(membership.getNftSale()))
                .saleCount(membership.getSaleCount())
                .salePrice(membership.getSalePrice())
                .purchaseStart(membership.getPurchaseStart())
                .purchaseEnd(membership.getPurchaseEnd())
                .contractAddress(membership.getContractAddress())
                .build();
    }

}
