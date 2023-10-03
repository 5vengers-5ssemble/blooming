package com.fivengers.blooming.project_application.adapter.out.persistence.repository;


import com.fivengers.blooming.project_application.adapter.out.persistence.mapper.ProjectApplicationMapper;
import com.fivengers.blooming.project_application.application.port.out.ProjectApplicationPort;
import com.fivengers.blooming.project_application.domain.ProjectApplication;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProjectApplicationPersistenceAdapter implements ProjectApplicationPort {

    private final ProjectApplicationMapper projectApplicationMapper;
    private final ProjectApplicationSpringJpaRepository projectApplicationSpringJpaRepository;

    @Override
    public void save(ProjectApplication application) {
        projectApplicationSpringJpaRepository.save(
                projectApplicationMapper.toJpaEntity(application));
    }

    @Override
    public Optional<ProjectApplication> findByMemberId(Long memberId) {
        return projectApplicationSpringJpaRepository.findByMemberId(memberId)
                .map(projectApplicationMapper::toDomain);
    }
}
