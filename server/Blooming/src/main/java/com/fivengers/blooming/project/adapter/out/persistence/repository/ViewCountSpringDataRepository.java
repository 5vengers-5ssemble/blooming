package com.fivengers.blooming.project.adapter.out.persistence.repository;

import com.fivengers.blooming.project.adapter.out.persistence.entity.ProjectJpaEntity;
import com.fivengers.blooming.project.adapter.out.persistence.entity.ViewCountJpaEntity;
import com.fivengers.blooming.project.domain.Project;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewCountSpringDataRepository extends JpaRepository<ViewCountJpaEntity, Long> {

    List<ViewCountJpaEntity> findAllByProject(ProjectJpaEntity project, Pageable pageable);
}
