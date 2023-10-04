package com.fivengers.blooming.artistscrap.adapter.out.persistence.repository;

import com.fivengers.blooming.artistscrap.adapter.out.persistence.entity.ArtistScrapJpaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistScrapSpringDataRepository extends JpaRepository<ArtistScrapJpaEntity, Long> {

    Optional<ArtistScrapJpaEntity> findByMemberJpaEntityIdAndArtistJpaEntityId(
            Long memberJpaEntityId, Long artistJpaEntityId);

    void deleteByMemberJpaEntityIdAndArtistJpaEntityId(Long memberJpaEntityId,
            Long artistJpaEntityId);

    boolean existsByArtistJpaEntityIdAndMemberJpaEntityId(Long artistJpaEntityId,
            Long memberJpaEntityId);
}
