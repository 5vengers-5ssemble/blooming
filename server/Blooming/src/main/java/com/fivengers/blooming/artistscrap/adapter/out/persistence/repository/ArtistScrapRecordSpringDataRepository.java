package com.fivengers.blooming.artistscrap.adapter.out.persistence.repository;

import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistJpaEntity;
import com.fivengers.blooming.artistscrap.adapter.out.persistence.entity.ArtistScrapRecordJpaEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistScrapRecordSpringDataRepository
        extends JpaRepository<ArtistScrapRecordJpaEntity, Long> {


    Optional<ArtistScrapRecordJpaEntity> findByStartDateOnWeekAndEndDateOnWeekAndArtistJpaEntity(
            LocalDateTime startDateOnWeek, LocalDateTime endDateOnWeek, ArtistJpaEntity artistJpaEntity);
}
