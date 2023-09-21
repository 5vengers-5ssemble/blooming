package com.fivengers.blooming.membership.adapter.in.web.dto;

import com.fivengers.blooming.membership.domain.NftSale;
import lombok.Builder;

@Builder
public record NftSaleResponse(Integer totalNftCount,
                              Integer soldNftCount,
                              Long totalNftAmount,
                              Long soldNftAmount) {

    public static NftSaleResponse from(NftSale nftSale) {
        return NftSaleResponse.builder()
                .totalNftCount(nftSale.getTotalNftCount())
                .soldNftCount(nftSale.getSoldNftCount())
                .totalNftAmount(nftSale.getTotalNftAmount())
                .soldNftAmount(nftSale.getSoldNftAmount())
                .build();
    }

}
