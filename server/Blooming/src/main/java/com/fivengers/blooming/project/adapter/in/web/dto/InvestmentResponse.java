package com.fivengers.blooming.project.adapter.in.web.dto;

import com.fivengers.blooming.project.domain.InvestmentGoods;
import com.fivengers.blooming.project.domain.InvestmentOverview;
import com.fivengers.blooming.project.domain.InvestmentStructure;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record InvestmentResponse(Overview overview,
                                 Structure structure,
                                 Goods goods
                                 ) {

    @Builder
    public record Overview(String publisher,
                           String type,
                           String redemptionType,
                           String financingPurpose,
                           Long pricePerAccount,
                           Long minimumPrice,
                           Long minimumFundingAmount,
                           Long maximumFundingAmount,
                           LocalDateTime fundingStartDate,
                           LocalDateTime fundingEndDate,
                           LocalDateTime investmentPublishedDate,
                           LocalDateTime investmentMaturedDate) {

        public static Overview from(InvestmentOverview overview) {
            return Overview.builder()
                    .publisher(overview.getPublisher())
                    .type(overview.getType())
                    .redemptionType(overview.getRedemptionType())
                    .financingPurpose(overview.getFinancingPurpose())
                    .pricePerAccount(overview.getPricePerAccount())
                    .minimumPrice(overview.getMinimumPrice())
                    .minimumFundingAmount(overview.getMinimumFundingAmount())
                    .maximumFundingAmount(overview.getMaximumFundingAmount())
                    .fundingStartDate(overview.getFundingStartedAt())
                    .fundingEndDate(overview.getFundingEndedAt())
                    .investmentPublishedDate(overview.getInvestmentPublishedAt())
                    .investmentMaturedDate(overview.getInvestmentMaturedAt())
                    .build();
        }
    }

    @Builder
    public record Structure() {

        public static Structure from(InvestmentStructure investmentStructure) {
            return Structure.builder().build();
        }
    }

    @Builder
    public record Goods() {

        public static Goods from(InvestmentGoods investmentGoods) {
            return Goods.builder().build();
        }
    }

    public static InvestmentResponse of(InvestmentOverview overview) {
        return InvestmentResponse.builder()
                .overview(Overview.from(overview))
                .build();
    }
}
