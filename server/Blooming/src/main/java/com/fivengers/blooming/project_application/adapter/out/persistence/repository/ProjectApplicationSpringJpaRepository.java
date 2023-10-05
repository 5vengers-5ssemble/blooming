package com.fivengers.blooming.project_application.adapter.out.persistence.repository;

import com.fivengers.blooming.project_application.adapter.out.persistence.entity.ProjectApplicationJpaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectApplicationSpringJpaRepository extends
        JpaRepository<ProjectApplicationJpaEntity, Long> {

    Optional<ProjectApplicationJpaEntity> findByMemberId(Long memberId);

}
