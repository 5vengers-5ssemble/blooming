package com.fivengers.blooming.project.adapter.in.web.dto;

import com.fivengers.blooming.project.domain.Concert;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record ConcertListResponse(Long id,
                                  String title,
                                  String description,
                                  String profileImg,
                                  LocalDateTime startDate,
                                  LocalDateTime endDate,
                                  Long totalProcess,
                                  Long nowProcess) {

    public static ConcertListResponse from(Concert concert) {
        return ConcertListResponse.builder()
                .id(concert.getId())
                .title(concert.getName())
                .description(concert.getDescription())
                .profileImg(concert.getPosterImgUrl())
                .startDate(concert.getStartedAt())
                .endDate(concert.getEndedAt())
                .totalProcess(concert.getTargetAmount())
                .nowProcess(concert.getFundingAmount())
                .build();
    }
}
