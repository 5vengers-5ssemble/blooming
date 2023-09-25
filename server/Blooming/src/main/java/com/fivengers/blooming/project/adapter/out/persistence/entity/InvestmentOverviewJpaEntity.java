package com.fivengers.blooming.project.adapter.out.persistence.entity;

import com.fivengers.blooming.global.audit.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "investment_overview")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InvestmentOverviewJpaEntity extends BaseTime {

    @Id
    @Column(name = "project_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "project_id")
    @MapsId
    private ProjectJpaEntity project;

    @Column(nullable = false)
    private String publisher;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String redemptionType;
    @Column(nullable = false)
    private String financingPurpose;
    @Column(nullable = false)
    private Long pricePerAccount;
    @Column(nullable = false)
    private Long minimumPrice;
    @Column(nullable = false)
    private Long minimumFundingAmount;
    @Column(nullable = false)
    private Long maximumFundingAmount;
    @Column(nullable = false)
    private LocalDateTime fundingStartedAt;
    @Column(nullable = false)
    private LocalDateTime fundingEndedAt;
    @Column(nullable = false)
    private LocalDateTime investmentPublishedAt;
    @Column(nullable = false)
    private LocalDateTime investmentMaturedAt;

    @Builder
    public InvestmentOverviewJpaEntity(
            Long id,
            ProjectJpaEntity project,
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
            LocalDateTime investmentMaturedAt) {
        this.id = id;
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
    }
}
