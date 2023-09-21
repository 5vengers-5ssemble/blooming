package com.fivengers.blooming.artist.adapter.out.persistence.repository;

import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistJpaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistSpringDataRepository extends JpaRepository<ArtistJpaEntity, Long> {

    List<ArtistJpaEntity> findByDeletedFalse();
}
