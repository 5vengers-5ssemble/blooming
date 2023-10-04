package com.fivengers.blooming.project_application.adapter.out.persistence.entity;

import com.fivengers.blooming.project.domain.ProjectType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectInfoInJpaEntity {

    @Column(nullable = false)
    private ProjectType projectType;

    private MakerInfoInJpaEntity makerInfo;

    @Column(nullable = false)
    private Long targetAmount;

    public ProjectInfoInJpaEntity(ProjectType projectType,
            MakerInfoInJpaEntity makerInfo,
            Long targetAmount) {
        this.projectType = projectType;
        this.makerInfo = makerInfo;
        this.targetAmount = targetAmount;
    }
}
