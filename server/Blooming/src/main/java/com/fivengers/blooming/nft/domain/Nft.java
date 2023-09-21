package com.fivengers.blooming.nft.domain;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.membership.domain.Membership;
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
    private Membership membership;
    private Artist artist;

    @Builder
    public Nft(Long id,
            String tokenId,
            String contractAddress,
            String symbol,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            Membership membership,
            Artist artist) {
        this.id = id;
        this.tokenId = tokenId;
        this.contractAddress = contractAddress;
        this.symbol = symbol;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.membership = membership;
        this.artist = artist;
    }
}
