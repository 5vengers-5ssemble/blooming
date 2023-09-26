package com.fivengers.blooming.artist.adapter.out.persistence.repository;

import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistVideoJpaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistVideoSpringDataRepository extends JpaRepository<ArtistVideoJpaEntity, Long> {

    List<ArtistVideoJpaEntity> findByArtistJpaEntityId(Long artistJpaEntityId);
}
