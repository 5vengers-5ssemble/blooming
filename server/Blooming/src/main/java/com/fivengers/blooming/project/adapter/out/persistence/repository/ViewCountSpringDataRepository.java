package com.fivengers.blooming.project.adapter.out.persistence.repository;

import com.fivengers.blooming.project.adapter.out.persistence.entity.ViewCountJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewCountSpringDataRepository extends JpaRepository<ViewCountJpaEntity, Long> {

}
