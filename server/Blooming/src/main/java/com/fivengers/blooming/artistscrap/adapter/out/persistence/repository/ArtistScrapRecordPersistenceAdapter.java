package com.fivengers.blooming.artistscrap.adapter.out.persistence.repository;

import com.fivengers.blooming.artistscrap.adapter.out.persistence.entity.ArtistScrapRecordJpaEntity;
import com.fivengers.blooming.artistscrap.adapter.out.persistence.mapper.ArtistScrapRecordMapper;
import com.fivengers.blooming.artistscrap.application.port.out.ArtistScrapRecordPort;
import com.fivengers.blooming.artistscrap.domain.ArtistScrapRecord;
import com.fivengers.blooming.global.exception.artistscrap.ArtistScrapRecordNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArtistScrapRecordPersistenceAdapter implements ArtistScrapRecordPort {

    private final ArtistScrapRecordSpringDataRepository artistScrapRecordSpringDataRepository;
    private final ArtistScrapRecordQueryRepository artistScrapRecordQueryRepository;
    private final ArtistScrapRecordMapper artistScrapRecordMapper;

    @Override
    @Transactional
    public ArtistScrapRecord save(ArtistScrapRecord artistScrapRecord) {
        return artistScrapRecordMapper
                .toDomain(artistScrapRecordSpringDataRepository
                        .save(artistScrapRecordMapper.toJpaEntity(artistScrapRecord)));
    }

    @Override
    public List<ArtistScrapRecord> findTopByArtistIdOrderByStartDateDesc(Long artistId, Long limit) {
        return artistScrapRecordQueryRepository
                .findByArtistIdOrderByStartDateDescLimit(artistId, limit).stream()
                .map(artistScrapRecordMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<ArtistScrapRecord> findOnWeek(LocalDateTime startDate,
            LocalDateTime endDate, Long artistId) {
        return artistScrapRecordSpringDataRepository
                .findByStartDateOnWeekAndEndDateOnWeekAndArtistJpaEntityId(
                        startDate, endDate, artistId)
                .map(artistScrapRecordMapper::toDomain);
    }

    @Override
    @Transactional
    public void update(ArtistScrapRecord artistScrapRecord) {
        ArtistScrapRecordJpaEntity record = artistScrapRecordSpringDataRepository
                .findById(artistScrapRecord.getId())
                .orElseThrow(ArtistScrapRecordNotFoundException::new);

        record.update(artistScrapRecord);
    }
}
