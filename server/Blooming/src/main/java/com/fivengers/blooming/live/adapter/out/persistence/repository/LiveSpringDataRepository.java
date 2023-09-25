package com.fivengers.blooming.live.adapter.out.persistence.repository;

import com.fivengers.blooming.live.adapter.out.persistence.entity.LiveJpaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiveSpringDataRepository extends JpaRepository<LiveJpaEntity, Long> {
    Optional<LiveJpaEntity> findLiveJpaEntityByIdAndEndedAtIsNull(Long id);
}
