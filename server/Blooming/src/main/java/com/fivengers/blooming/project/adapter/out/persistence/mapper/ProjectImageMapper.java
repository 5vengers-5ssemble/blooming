package com.fivengers.blooming.project.adapter.out.persistence.mapper;

import com.fivengers.blooming.project.adapter.out.persistence.entity.ProjectImageJpaEntity;
import com.fivengers.blooming.project.domain.ProjectImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ProjectImageMapper {

    private final ProjectMapper projectMapper;

    public ProjectImage toDomain(ProjectImageJpaEntity projectImageJpaEntity) {

        return ProjectImage.builder()
                .id(projectImageJpaEntity.getId())
                .imageUrl(projectImageJpaEntity.getImageUrl())
                .project(projectMapper.toDomain(projectImageJpaEntity.getProject()))
                .build();
    }

    public ProjectImageJpaEntity toJpaEntity(ProjectImage projectImage) {

        return ProjectImageJpaEntity.builder()
                .id(projectImage.getId())
                .imageUrl(projectImage.getImageUrl())
                .deleted(false)
                .project(projectMapper.toJpaEntity(projectImage.getProject()))
                .build();
    }
}
