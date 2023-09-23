package com.fivengers.blooming.form.application.port.in.dto;

import com.fivengers.blooming.form.domain.StoryInfo;

public record StoryInfoRequest(String summary,
                               String teaser,
                               MoreInfoRequest moreInfo,
                               Long budget) {

    public StoryInfo toDomain() {
        return StoryInfo.builder()
                .introduction(summary)
                .teaserVideoUrl(teaser)
                .moreInfo(moreInfo.toDomain())
                .budget(budget)
                .build();
    }
}
