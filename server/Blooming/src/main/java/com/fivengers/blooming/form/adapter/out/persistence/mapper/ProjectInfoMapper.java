package com.fivengers.blooming.form.adapter.out.persistence.mapper;

import com.fivengers.blooming.form.adapter.out.persistence.entity.ProjectInfoInForm;
import com.fivengers.blooming.form.domain.ProjectInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectInfoMapper {

    private final MakerInfoMapper makerInfoMapper;

    public ProjectInfo toDomain(ProjectInfoInForm projectInfo) {

        return new ProjectInfo(projectInfo.getCategory(),
                makerInfoMapper.toDomain(projectInfo.getMakerInfo()),
                projectInfo.getTargetAmount());
    }

    public ProjectInfoInForm toInForm(ProjectInfo projectInfo) {

        return new ProjectInfoInForm(projectInfo.getCategory(),
                makerInfoMapper.toInForm(projectInfo.getMakerInfo()),
                projectInfo.getTargetAmount());
    }
}
