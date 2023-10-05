package com.fivengers.blooming.project.adapter.in.web.dto;

import com.fivengers.blooming.project.domain.Project;
import com.fivengers.blooming.project.domain.ProjectType;

public record AdvertisingProjectResponse(ProjectType type,
                                         Long fundingId,
                                         String mainFundingImg,
                                         String mainFundingTitle,
                                         String mainFundingDesc) {

    public static AdvertisingProjectResponse from(Project project) {

        return new AdvertisingProjectResponse(
                project.getDtype(),
                project.getId(),
                project.getProfileImg(),
                project.getName(),
                project.getIntroduction());
    }
}
