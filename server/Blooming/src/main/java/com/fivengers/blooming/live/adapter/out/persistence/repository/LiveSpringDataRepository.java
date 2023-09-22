package com.fivengers.blooming.live.adapter.out.persistence.repository;

import com.fivengers.blooming.live.adapter.out.persistence.entity.LiveJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiveSpringDataRepository extends JpaRepository<LiveJpaEntity, Long> {

}
