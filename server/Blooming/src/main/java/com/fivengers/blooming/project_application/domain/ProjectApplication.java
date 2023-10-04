package com.fivengers.blooming.project_application.domain;


import com.fivengers.blooming.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectApplication {

    private Long id;
    private ProjectInfo projectInfo;
    private BasicInfo basicInfo;
    private StoryInfo storyInfo;
    private PolicyInfo policyInfo;
    private SettlementInfo settlementInfo;
    private ProjectApplicationState state;
    private Member member;

    @Builder
    public ProjectApplication(Long id, ProjectInfo projectInfo, BasicInfo basicInfo,
            StoryInfo storyInfo, PolicyInfo policyInfo, SettlementInfo settlementInfo,
            ProjectApplicationState state, Member member) {
        this.id = id;
        this.projectInfo = projectInfo;
        this.basicInfo = basicInfo;
        this.storyInfo = storyInfo;
        this.policyInfo = policyInfo;
        this.settlementInfo = settlementInfo;
        this.state = state;
        this.member = member;
    }
}
