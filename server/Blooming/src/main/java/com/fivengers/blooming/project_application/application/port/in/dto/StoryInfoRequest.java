package com.fivengers.blooming.project_application.application.port.in.dto;

import com.fivengers.blooming.project_application.domain.StoryInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record StoryInfoRequest(@NotBlank String introduction,
                               @NotBlank String teaser,
                               @NotNull @Valid MoreInfoRequest moreInfo,
                               @NotNull Long budget) {

    public StoryInfo toDomain() {
        return StoryInfo.builder()
                .introduction(introduction)
                .description(moreInfo.description())
                .listImage(moreInfo.listImage())
                .compositionImage(moreInfo.compositionImage())
                .teaserVideoUrl(teaser)
                .budget(budget)
                .build();
    }
}
