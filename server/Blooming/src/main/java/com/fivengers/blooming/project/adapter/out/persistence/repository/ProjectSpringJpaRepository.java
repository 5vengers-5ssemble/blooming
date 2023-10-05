package com.fivengers.blooming.project.adapter.out.persistence.repository;

import com.fivengers.blooming.project.adapter.out.persistence.entity.ProjectJpaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectSpringJpaRepository extends JpaRepository<ProjectJpaEntity, Long> {

    List<ProjectJpaEntity> findTop5ByArtist_IdOrderByCreatedAtDesc(Long artistId);

    @Query("select p from ProjectJpaEntity p where p.id between 1 and 6")
    List<ProjectJpaEntity> findAllById();
}
