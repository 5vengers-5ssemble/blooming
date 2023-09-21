package com.fivengers.blooming.nft.adapter.in.web.dto;

import com.fivengers.blooming.nft.domain.Nft;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record NftDetailsResponse(String tokenId,
                                 String contractAddress,
                                 String symbol,
                                 LocalDateTime createdAt,
                                 LocalDateTime modifiedAt) {

    public static NftDetailsResponse from(Nft nft) {
        return NftDetailsResponse.builder()
                .tokenId(nft.getTokenId())
                .contractAddress(nft.getContractAddress())
                .symbol(nft.getSymbol())
                .createdAt(nft.getCreatedAt())
                .modifiedAt(nft.getModifiedAt())
                .build();
    }
}
