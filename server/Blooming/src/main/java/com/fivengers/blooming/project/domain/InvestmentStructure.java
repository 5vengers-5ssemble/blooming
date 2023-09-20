package com.fivengers.blooming.project.domain;


import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class InvestmentStructure {

    private Project project;

    private Long totalAmount;
    private Long customerTransaction;
    private Integer dividendRatio;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public InvestmentStructure(
            Project project,
            Long totalAmount,
            Long customerTransaction,
            Integer dividendRatio,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt) {
        this.project = project;
        this.totalAmount = totalAmount;
        this.customerTransaction = customerTransaction;
        this.dividendRatio = dividendRatio;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
