package com.fivengers.blooming.project.adapter.in.web.dto;

import com.fivengers.blooming.project.domain.Concert;
import com.fivengers.blooming.project.domain.InvestmentOverview;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record PastConcertResponse(Long id,
                                  String name,
                                  String posterImg,
                                  LocalDateTime publishedDate,
                                  Integer revenuePercent,
                                  Long targetAmount,
                                  Long fundingAmount) {

    public static PastConcertResponse from(Concert concert, InvestmentOverview overview) {
        return PastConcertResponse.builder()
                .id(concert.getId())
                .name(concert.getName())
                .posterImg(concert.getPosterImgUrl())
                .publishedDate(overview.getInvestmentPublishedAt())
                .revenuePercent(concert.getRevenuePercent())
                .targetAmount(concert.getTargetAmount())
                .fundingAmount(concert.getFundingAmount())
                .build();
    }
}
