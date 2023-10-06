package com.fivengers.blooming.project_application.adapter.in.web.dto;

import com.fivengers.blooming.member.adapter.in.web.dto.MemberResponse;
import com.fivengers.blooming.project_application.application.port.in.dto.BasicInfoRequest;
import com.fivengers.blooming.project_application.application.port.in.dto.PolicyInfoRequest;
import com.fivengers.blooming.project_application.application.port.in.dto.ProjectInfoRequest;
import com.fivengers.blooming.project_application.application.port.in.dto.SettlementInfoRequest;
import com.fivengers.blooming.project_application.application.port.in.dto.StoryInfoRequest;
import com.fivengers.blooming.project_application.domain.BasicInfo;
import com.fivengers.blooming.project_application.domain.PolicyInfo;
import com.fivengers.blooming.project_application.domain.ProjectApplication;
import com.fivengers.blooming.project_application.domain.ProjectInfo;
import com.fivengers.blooming.project_application.domain.SettlementInfo;
import com.fivengers.blooming.project_application.domain.StoryInfo;
import lombok.Builder;

@Builder
public record ProjectApplicationDetailsResponse(ProjectInfo projectInfo,
                                                BasicInfo basicInfo,
                                                StoryInfo storyInfo,
                                                PolicyInfo policyInfo,
                                                SettlementInfo settlementInfo,
                                                MemberResponse memberResponse) {

    public static ProjectApplicationDetailsResponse from(ProjectApplication projectApplication,
            MemberResponse memberResponse) {
        return ProjectApplicationDetailsResponse.builder()
                .projectInfo(projectApplication.getProjectInfo())
                .settlementInfo(projectApplication.getSettlementInfo())
                .basicInfo(projectApplication.getBasicInfo())
                .storyInfo(projectApplication.getStoryInfo())
                .policyInfo(projectApplication.getPolicyInfo())
                .memberResponse(memberResponse)
                .build();
    }
}
