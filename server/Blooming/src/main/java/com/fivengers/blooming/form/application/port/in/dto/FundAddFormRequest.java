package com.fivengers.blooming.form.application.port.in.dto;

import com.fivengers.blooming.form.domain.FundAddForm;
import lombok.Builder;

@Builder
public record FundAddFormRequest(ProjectInfoRequest projectInfo,
                                 DefaultInfoRequest defaultInfo,
                                 StoryInfoRequest storyInfo,
                                 PolicyInfoRequest policyInfo,
                                 RepresentInfoRequest representInfo) {

    public FundAddForm toDomain() {

        return FundAddForm.builder()
                .projectInfo(projectInfo.toDomain())
                .defaultInfo(defaultInfo.toDomain())
                .storyInfo(storyInfo.toDomain())
                .policyInfo(policyInfo.toDomain())
                .representInfo(representInfo.toDomain())
                .build();
    }
}
