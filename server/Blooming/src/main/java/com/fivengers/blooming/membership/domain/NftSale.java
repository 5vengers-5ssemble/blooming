package com.fivengers.blooming.membership.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class NftSale {

    private Long id;
    private Integer totalNftCount;
    private Integer soldNftCount;
    private Long totalNftAmount;
    private Long soldNftAmount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public NftSale(Long id,
                   Integer totalNftCount,
                   Integer soldNftCount,
                   Long totalNftAmount,
                   Long soldNftAmount,
                   LocalDateTime createdAt,
                   LocalDateTime modifiedAt) {
        this.id = id;
        this.totalNftCount = totalNftCount;
        this.soldNftCount = soldNftCount;
        this.totalNftAmount = totalNftAmount;
        this.soldNftAmount = soldNftAmount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
