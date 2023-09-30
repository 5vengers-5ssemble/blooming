package com.fivengers.blooming.artist.adapter.out.persistence.repository;

import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistApplicationJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistApplicationSpringDataRepository extends JpaRepository<ArtistApplicationJpaEntity, Long> {

}
