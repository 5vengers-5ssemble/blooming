package com.fivengers.blooming.project.adapter.out.persistence.mapper;


import com.fivengers.blooming.artist.adapter.out.persistence.mapper.ArtistMapper;
import com.fivengers.blooming.project.adapter.out.persistence.entity.ProjectJpaEntity;
import com.fivengers.blooming.project.domain.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectMapper {

    private final ArtistMapper artistMapper;

    public Project toDomain(ProjectJpaEntity projectJpaEntity) {
        return new Project(
                projectJpaEntity.getId(),
                projectJpaEntity.getName(),
                projectJpaEntity.getFundingAmount(),
                projectJpaEntity.getTargetAmount(),
                projectJpaEntity.getStartedAt(),
                projectJpaEntity.getEndedAt(),
                projectJpaEntity.getDescription(),
                projectJpaEntity.getCreatedAt(),
                projectJpaEntity.getModifiedAt(),
                artistMapper.toDomain(projectJpaEntity.getArtist())
        );
    }

    public ProjectJpaEntity toJpaEntity(Project project) {
        return new ProjectJpaEntity(
                project.getId(),
                project.getName(),
                project.getFundingAmount(),
                project.getTargetAmount(),
                project.getStartedAt(),
                project.getEndedAt(),
                project.getDescription(),
                false,
                artistMapper.toJpaEntity(project.getArtist())
        );
    }
}
