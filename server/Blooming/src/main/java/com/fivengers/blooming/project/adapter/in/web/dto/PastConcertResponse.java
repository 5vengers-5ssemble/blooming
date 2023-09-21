package com.fivengers.blooming.project.adapter.in.web.dto;

import com.fivengers.blooming.project.domain.Concert;
import lombok.Builder;

@Builder
public record PastConcertResponse(Long id,
                                  String name,
                                  String posterImg,
                                  String publishedDate,
                                  Integer revenuePercent,
                                  Long targetAmount,
                                  Long fundingAmount) {

    public static PastConcertResponse from(Concert concert) {
        return PastConcertResponse.builder()
                .id(concert.getId())
                .name(concert.getName())
                .posterImg(concert.getPosterImgUrl())
                .revenuePercent(concert.getRevenuePercent())
                .targetAmount(concert.getTargetAmount())
                .fundingAmount(concert.getFundingAmount())
                .build();
    }
}
