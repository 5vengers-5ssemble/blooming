package com.fivengers.blooming.membership.domain;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.nft.domain.Nft;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Membership {

    private Long id;
    private String title;
    private String description;
    private Integer season;
    private LocalDateTime seasonStart;
    private LocalDateTime seasonEnd;
    private LocalDateTime purchaseStart;
    private LocalDateTime purchaseEnd;
    private Integer saleCount;
    private String thumbnailUrl;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Artist artist;
    private NftSale nftSale;

    @Builder
    public Membership(Long id,
                      String title,
                      String description,
                      Integer season,
                      LocalDateTime seasonStart,
                      LocalDateTime seasonEnd,
                      LocalDateTime purchaseStart,
                      LocalDateTime purchaseEnd,
                      Integer saleCount,
                      String thumbnailUrl,
                      LocalDateTime createdAt,
                      LocalDateTime modifiedAt,
                      Artist artist,
                      NftSale nftSale) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.season = season;
        this.seasonStart = seasonStart;
        this.seasonEnd = seasonEnd;
        this.purchaseStart = purchaseStart;
        this.purchaseEnd = purchaseEnd;
        this.saleCount = saleCount;
        this.thumbnailUrl = thumbnailUrl;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.artist = artist;
        this.nftSale = nftSale;
    }
}
