package com.fivengers.blooming.project.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class InvestmentOverview {

    private Project project;

    private String publisher;
    private String type;
    private String redemptionType;
    private String financingPurpose;
    private Long pricePerAccount;
    private Long minimumPrice;
    private Long minimumFundingAmount;
    private Long maximumFundingAmount;
    private LocalDateTime fundingStartedAt;
    private LocalDateTime fundingEndedAt;
    private LocalDateTime investmentPublishedAt;
    private LocalDateTime investmentMaturedAt;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public InvestmentOverview(
            Project project,
            String publisher,
            String type,
            String redemptionType,
            String financingPurpose,
            Long pricePerAccount,
            Long minimumPrice,
            Long minimumFundingAmount,
            Long maximumFundingAmount,
            LocalDateTime fundingStartedAt,
            LocalDateTime fundingEndedAt,
            LocalDateTime investmentPublishedAt,
            LocalDateTime investmentMaturedAt,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt) {
        this.project = project;
        this.publisher = publisher;
        this.type = type;
        this.redemptionType = redemptionType;
        this.financingPurpose = financingPurpose;
        this.pricePerAccount = pricePerAccount;
        this.minimumPrice = minimumPrice;
        this.minimumFundingAmount = minimumFundingAmount;
        this.maximumFundingAmount = maximumFundingAmount;
        this.fundingStartedAt = fundingStartedAt;
        this.fundingEndedAt = fundingEndedAt;
        this.investmentPublishedAt = investmentPublishedAt;
        this.investmentMaturedAt = investmentMaturedAt;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
