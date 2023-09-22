package com.fivengers.blooming.project.adapter.in.web.dto;

import com.fivengers.blooming.project.domain.Activity;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record ActivityListResponse(Long id,
                                   String title,
                                   String description,
                                   String profileImg,
                                   LocalDateTime startDate,
                                   LocalDateTime endDate,
                                   Long totalProcess,
                                   Long nowProcess) {

    public static ActivityListResponse from(Activity activity) {
        return ActivityListResponse.builder()
                .id(activity.getId())
                .title(activity.getName())
                .description(activity.getDescription())
                .profileImg(activity.getAlbumImgUrl())
                .startDate(activity.getStartedAt())
                .endDate(activity.getEndedAt())
                .totalProcess(activity.getTargetAmount())
                .nowProcess(activity.getFundingAmount())
                .build();
    }
}
