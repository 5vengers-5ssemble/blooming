package com.fivengers.blooming.artist.adapter.in.web.dto;

import com.fivengers.blooming.project.domain.Project;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record ArtistProjectResponse(Long id,
                                    String thumbnail,
                                    String title,
                                    String introduction,
                                    Long targetAmount,
                                    Long fundingAmount,
                                    LocalDateTime startedAt,
                                    LocalDateTime endedAt) {

    public static ArtistProjectResponse empty() {

        return ArtistProjectResponse.builder().build();
    }

    public static ArtistProjectResponse from(Project project) {
        return ArtistProjectResponse.builder()
                .id(project.getId())
                .thumbnail(project.getProfileImg())
                .title(project.getName())
                .introduction(project.getIntroduction())
                .targetAmount(project.getTargetAmount())
                .fundingAmount(project.getFundingAmount())
                .startedAt(project.getStartedAt())
                .endedAt(project.getEndedAt())
                .build();
    }
}
