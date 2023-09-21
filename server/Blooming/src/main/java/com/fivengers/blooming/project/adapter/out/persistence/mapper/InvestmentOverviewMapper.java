package com.fivengers.blooming.project.adapter.out.persistence.mapper;

import com.fivengers.blooming.project.adapter.out.persistence.entity.InvestmentOverviewJpaEntity;
import com.fivengers.blooming.project.domain.InvestmentOverview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InvestmentOverviewMapper {

    private final ProjectMapper projectMapper;

    public InvestmentOverview toDomain(InvestmentOverviewJpaEntity investmentOverview) {

        return InvestmentOverview.builder()
                .publisher(investmentOverview.getPublisher())
                .type(investmentOverview.getType())
                .redemptionType(investmentOverview.getRedemptionType())
                .financingPurpose(investmentOverview.getFinancingPurpose())
                .pricePerAccount(investmentOverview.getPricePerAccount())
                .minimumPrice(investmentOverview.getMinimumPrice())
                .minimumFundingAmount(investmentOverview.getMinimumFundingAmount())
                .maximumFundingAmount(investmentOverview.getMaximumFundingAmount())
                .createdAt(investmentOverview.getCreatedAt())
                .modifiedAt(investmentOverview.getModifiedAt())
                .project(projectMapper.toDomain(investmentOverview.getProject()))
                .build();
    }

    public InvestmentOverviewJpaEntity toJpaEntity(InvestmentOverview investmentOverview) {

        return InvestmentOverviewJpaEntity.builder()
                .publisher(investmentOverview.getPublisher())
                .type(investmentOverview.getType())
                .redemptionType(investmentOverview.getRedemptionType())
                .financingPurpose(investmentOverview.getFinancingPurpose())
                .pricePerAccount(investmentOverview.getPricePerAccount())
                .minimumPrice(investmentOverview.getMinimumPrice())
                .minimumFundingAmount(investmentOverview.getMinimumFundingAmount())
                .maximumFundingAmount(investmentOverview.getMaximumFundingAmount())
                .project(projectMapper.toJpaEntity(investmentOverview.getProject()))
                .build();
    }
}
