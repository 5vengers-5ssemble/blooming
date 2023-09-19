package com.fivengers.blooming.live.adapter.out.persistence.repository;

import com.fivengers.blooming.live.adapter.out.persistence.entity.LiveJpaEntity;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LiveSpringDataRepository extends JpaRepository<LiveJpaEntity, Long> {
    List<LiveJpaEntity> findByTitleContainingAndEndedAtIsNull(String title, Pageable pageable);

    List<LiveJpaEntity> findByArtistJpaEntityStageNameContainingAndEndedAtIsNull(String stageName, Pageable pageable);
}
