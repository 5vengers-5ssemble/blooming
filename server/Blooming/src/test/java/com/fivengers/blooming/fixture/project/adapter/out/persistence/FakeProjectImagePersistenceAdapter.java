package com.fivengers.blooming.fixture.project.adapter.out.persistence;

import com.fivengers.blooming.project.application.port.out.ProjectImagePort;
import com.fivengers.blooming.project.domain.Project;
import com.fivengers.blooming.project.domain.ProjectImage;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeProjectImagePersistenceAdapter implements ProjectImagePort {

    private final Map<Long, ProjectImage> store = new HashMap<>();
    private Long autoIncrementId = 1L;

    public ProjectImage save(ProjectImage projectImage) {
        if (isPersistenceObject(projectImage)) {
            store.put(projectImage.getId(), projectImage);
            return projectImage;
        }
        return persist(projectImage);
    }

    private static boolean isPersistenceObject(ProjectImage projectImage) {
        return projectImage.getId() != null;
    }

    private ProjectImage persist(ProjectImage projectImage) {
        LocalDateTime now = LocalDateTime.now();
        ProjectImage persistedConcert = ProjectImage.builder()
                .id(autoIncrementId)
                .imageUrl(projectImage.getImageUrl())
                .project(projectImage.getProject())
                .build();
        store.put(autoIncrementId, persistedConcert);
        autoIncrementId++;
        return persistedConcert;
    }

    @Override
    public List<ProjectImage> findAllByProjectId(Long id) {
        return store.values().stream()
                .filter(projectImage ->
                        projectImage.getProject().getId().equals(id))
                .toList();
    }
}
