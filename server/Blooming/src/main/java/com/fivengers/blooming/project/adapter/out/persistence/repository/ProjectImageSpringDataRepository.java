package com.fivengers.blooming.project.adapter.out.persistence.repository;

import com.fivengers.blooming.project.adapter.out.persistence.entity.ProjectImageJpaEntity;
import com.fivengers.blooming.project.adapter.out.persistence.entity.ProjectJpaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 사용하지 않음
 */
public interface ProjectImageSpringDataRepository extends JpaRepository<ProjectImageJpaEntity, Long> {

    List<ProjectImageJpaEntity> findAllByProjectId(Long id);
}
