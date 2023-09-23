package com.fivengers.blooming.form.adapter.out.persistence.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FundAddFormJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fund_add_form_id")
    private Long id;
    @Embedded
    private ProjectInfoInForm projectInfo;
    @Embedded
    private DefaultInfoInForm defaultInfo;
    @Embedded
    private StoryInfoInForm storyInfo;
    @Embedded
    private PolicyInfoInForm policyInfo;
    @Embedded
    private RepresentInfoInForm representInfo;

    @Builder
    public FundAddFormJpaEntity(ProjectInfoInForm projectInfo, DefaultInfoInForm defaultInfo, StoryInfoInForm storyInfo,
            PolicyInfoInForm policyInfo, RepresentInfoInForm representInfo) {
        this.projectInfo = projectInfo;
        this.defaultInfo = defaultInfo;
        this.storyInfo = storyInfo;
        this.policyInfo = policyInfo;
        this.representInfo = representInfo;
    }
}
