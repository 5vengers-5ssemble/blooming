package com.fivengers.blooming.project.adapter.in.web.dto;

import com.fivengers.blooming.project.domain.Concert;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record ConcertResponse(String posterImg,
                              String name,
                              String intro,
                              String desc,
                              LocalDateTime startedAt,
                              LocalDateTime endedAt,
                              Long targetAmount,
                              Long fundingAmount,
                              String setlistImg,
                              String teaserVideoUrl,
                              String concertGoodsImg) {

    public static ConcertResponse from(Concert concert){
        return ConcertResponse.builder()
                .posterImg(concert.getPosterImgUrl())
                .name(concert.getName())
                .intro(concert.getIntroduction())
                .desc(concert.getDescription())
                .startedAt(concert.getStartedAt())
                .endedAt(concert.getEndedAt())
                .targetAmount(concert.getTargetAmount())
                .fundingAmount(concert.getFundingAmount())
                .setlistImg(concert.getSetlistImgUrl())
                .teaserVideoUrl(concert.getTeaserVideoUrl())
                .concertGoodsImg(concert.getGoodsImgUrl())
                .build();
    }
}
