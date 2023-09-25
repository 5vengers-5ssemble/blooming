package com.fivengers.blooming.form.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FundAddForm {

    private ProjectInfo projectInfo;
    private DefaultInfo defaultInfo;
    private StoryInfo storyInfo;
    private PolicyInfo policyInfo;
    private RepresentInfo representInfo;

    @Builder
    public FundAddForm(ProjectInfo projectInfo, DefaultInfo defaultInfo, StoryInfo storyInfo,
            PolicyInfo policyInfo, RepresentInfo representInfo) {
        this.projectInfo = projectInfo;
        this.defaultInfo = defaultInfo;
        this.storyInfo = storyInfo;
        this.policyInfo = policyInfo;
        this.representInfo = representInfo;
    }
}
