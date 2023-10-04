package com.fivengers.blooming.emoji.adapter.out.pesistence.repository;

import com.fivengers.blooming.emoji.adapter.out.pesistence.entity.MotionModelJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotionModelSpringDataRepository extends JpaRepository<MotionModelJpaEntity, Long> {

}
