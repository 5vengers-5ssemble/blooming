package com.fivengers.blooming.project.adapter.in.web.dto;

import com.fivengers.blooming.project.domain.Activity;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record ActivityResponse(Long id,
                               String albumImg,
                               String name,
                               String intro,
                               String desc,
                               LocalDateTime startedAt,
                               LocalDateTime endedAt,
                               Long targetAmount,
                               Long fundingAmount,
                               String tracklistImg,
                               String teaserVideoUrl,
                               String compositionImg) {

    public static ActivityResponse from(Activity activity) {
        return ActivityResponse.builder()
                .id(activity.getId())
                .albumImg(activity.getAlbumImgUrl())
                .name(activity.getName())
                .intro(activity.getIntroduction())
                .desc(activity.getDescription())
                .startedAt(activity.getStartedAt())
                .endedAt(activity.getEndedAt())
                .targetAmount(activity.getTargetAmount())
                .fundingAmount(activity.getFundingAmount())
                .tracklistImg(activity.getTracklistImgUrl())
                .teaserVideoUrl(activity.getTeaserVideoUrl())
                .compositionImg(activity.getCompositionImgUrl())
                .build();
    }
}
