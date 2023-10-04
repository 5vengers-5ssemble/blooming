package com.fivengers.blooming.project.adapter.in.web.dto;

import com.fivengers.blooming.project.domain.Activity;
import com.fivengers.blooming.project.domain.InvestmentOverview;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record PastActivityResponse(Long id,
                                   String name,
                                   String albumImg,
                                   LocalDateTime publishedDate,
                                   Integer revenuePercent,
                                   Long targetAmount,
                                   Long fundingAmount) {

    public static PastActivityResponse from(Activity activity, InvestmentOverview overview) {
        return PastActivityResponse.builder()
                .id(activity.getId())
                .name(activity.getName())
                .albumImg(activity.getAlbumImgUrl())
                .publishedDate(overview.getInvestmentPublishedAt())
                .revenuePercent(activity.getRevenuePercent())
                .targetAmount(activity.getTargetAmount())
                .fundingAmount(activity.getFundingAmount())
                .build();
    }
}
