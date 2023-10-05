package com.fivengers.blooming.project.adapter.out.persistence.repository;


import com.fivengers.blooming.project.adapter.out.persistence.mapper.ProjectMapper;
import com.fivengers.blooming.project.application.port.out.ProjectPort;
import com.fivengers.blooming.project.domain.Project;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProjectPersistenceAdapter implements ProjectPort {

    private final ProjectMapper projectMapper;
    private final ProjectSpringJpaRepository projectSpringJpaRepository;

    @Override
    public List<Project> findAllByArtistId(Long artistId) {
        return projectSpringJpaRepository
                .findTop5ByArtist_IdOrderByCreatedAtDesc(artistId).stream()
                .map(projectMapper::toDomain)
                .toList();
    }

    @Override
    public List<Project> findAllById() {
        return projectSpringJpaRepository.findAllById().stream()
                .map(projectMapper::toDomain)
                .toList();
    }
}
