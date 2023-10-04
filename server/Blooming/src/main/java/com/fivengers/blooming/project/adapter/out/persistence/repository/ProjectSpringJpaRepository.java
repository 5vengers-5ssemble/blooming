package com.fivengers.blooming.project.adapter.out.persistence.repository;

import com.fivengers.blooming.project.adapter.out.persistence.entity.ProjectJpaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectSpringJpaRepository extends JpaRepository<ProjectJpaEntity, Long> {

    List<ProjectJpaEntity> findTop5ByArtist_IdOrderByCreatedAtDesc(Long artistId);
}
