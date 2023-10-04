package com.fivengers.blooming.project_application.domain;

import com.fivengers.blooming.project.domain.ProjectType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectInfo {

    private ProjectType projectType;

    private MakerInfo makerInfo;

    private Long targetAmount;

    public ProjectInfo(ProjectType projectType, MakerInfo makerInfo, Long targetAmount) {
        this.projectType = projectType;
        this.makerInfo = makerInfo;
        this.targetAmount = targetAmount;
    }
}
