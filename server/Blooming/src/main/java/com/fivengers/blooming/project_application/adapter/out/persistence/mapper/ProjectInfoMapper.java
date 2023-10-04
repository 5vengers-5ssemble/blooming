package com.fivengers.blooming.project_application.adapter.out.persistence.mapper;

import com.fivengers.blooming.project_application.adapter.out.persistence.entity.ProjectInfoInJpaEntity;
import com.fivengers.blooming.project_application.domain.ProjectInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectInfoMapper {

    private final MakerInfoMapper makerInfoMapper;

    public ProjectInfo toDomain(ProjectInfoInJpaEntity projectInfo) {

        return new ProjectInfo(
                projectInfo.getProjectType(),
                makerInfoMapper.toDomain(projectInfo.getMakerInfo()),
                projectInfo.getTargetAmount());
    }

    public ProjectInfoInJpaEntity toInJpaEntity(ProjectInfo projectInfo) {

        return new ProjectInfoInJpaEntity(
                projectInfo.getProjectType(),
                makerInfoMapper.toInJpaEntity(projectInfo.getMakerInfo()),
                projectInfo.getTargetAmount());
    }
}
