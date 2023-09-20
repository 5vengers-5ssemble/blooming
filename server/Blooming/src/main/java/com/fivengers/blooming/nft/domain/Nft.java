package com.fivengers.blooming.nft.domain;

import com.fivengers.blooming.artist.domain.Artist;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Nft {

    private Long id;
    private String tokenId;
    private String contractAddress;
    private String symbol;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Artist artist;

    @Builder
    public Nft(Long id,
            String tokenId,
            String contractAddress,
            String symbol,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            Artist artist) {
        this.id = id;
        this.tokenId = tokenId;
        this.contractAddress = contractAddress;
        this.symbol = symbol;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.artist = artist;
    }
}
