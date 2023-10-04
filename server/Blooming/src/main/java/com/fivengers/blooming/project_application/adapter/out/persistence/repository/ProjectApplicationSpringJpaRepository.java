package com.fivengers.blooming.project_application.adapter.out.persistence.repository;

import com.fivengers.blooming.project_application.adapter.out.persistence.entity.ProjectApplicationJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectApplicationSpringJpaRepository extends
        JpaRepository<ProjectApplicationJpaEntity, Long> {

}
