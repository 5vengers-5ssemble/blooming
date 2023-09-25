package com.fivengers.blooming.fixture.artistscrap.adapter.out.persistence;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.artistscrap.application.port.out.ArtistScrapRecordPort;
import com.fivengers.blooming.artistscrap.domain.ArtistScrapRecord;
import com.fivengers.blooming.global.exception.artistscrap.ArtistScrapRecordNotFoundException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FakeArtistScrapRecordPersistenceAdapter implements ArtistScrapRecordPort {

    private final Map<Long, ArtistScrapRecord> store = new HashMap<>();
    private Long autoIncrementId = 1L;

    @Override
    public ArtistScrapRecord save(ArtistScrapRecord artistScrapRecord) {
        if (isPersistenceObject(artistScrapRecord)) {
            store.put(artistScrapRecord.getId(), artistScrapRecord);
            return artistScrapRecord;
        }
        return persist(artistScrapRecord);
    }

    @Override
    public List<ArtistScrapRecord> findTopByArtistIdOrderByStartDateDesc(Long artistId, Long limit) {
        return store.values().stream()
                .filter(record -> record.getArtist().getId().equals(artistId))
                .sorted(Comparator.comparing(ArtistScrapRecord::getStartDateOnWeek).reversed())
                .limit(limit)
                .toList();
    }

    @Override
    public Optional<ArtistScrapRecord> findOnWeek(LocalDateTime startDate, LocalDateTime endDate,
            Artist artist) {
        return store.values().stream()
                .peek(record -> System.out.println(record.getId()))
                .peek(record -> System.out.println(record.getStartDateOnWeek() + ", " + startDate))
                .filter(record -> record.getStartDateOnWeek().isEqual(startDate))
                .peek(record -> System.out.println(record.getEndDateOnWeek() + ", " + endDate))
                .filter(record -> record.getEndDateOnWeek().isEqual(endDate))
                .filter(record -> record.getArtist().getId().equals(artist.getId()))
                .findFirst();
    }

    @Override
    public void update(ArtistScrapRecord artistScrapRecord) {
        ArtistScrapRecord scrapRecord = store.values().stream()
                .filter(record -> record.getId().equals(artistScrapRecord.getId()))
                .findFirst()
                .orElseThrow(ArtistScrapRecordNotFoundException::new);

        ArtistScrapRecord updatedRecord = ArtistScrapRecord.builder()
                .id(scrapRecord.getId())
                .scrapCount(scrapRecord.getScrapCount())
                .startDateOnWeek(scrapRecord.getStartDateOnWeek())
                .endDateOnWeek(scrapRecord.getEndDateOnWeek())
                .createdAt(scrapRecord.getCreatedAt())
                .modifiedAt(LocalDateTime.now())
                .artist(scrapRecord.getArtist())
                .build();

        store.put(artistScrapRecord.getId(), updatedRecord);
    }

    private boolean isPersistenceObject(ArtistScrapRecord artistScrapRecord) {
        return artistScrapRecord.getId() != null;
    }

    private ArtistScrapRecord persist(ArtistScrapRecord artistScrapRecord) {
        LocalDateTime now = LocalDateTime.now();
        ArtistScrapRecord persistArtistScrap = ArtistScrapRecord.builder()
                .id(autoIncrementId)
                .scrapCount(artistScrapRecord.getScrapCount())
                .createdAt(now)
                .modifiedAt(now)
                .startDateOnWeek(artistScrapRecord.getStartDateOnWeek())
                .endDateOnWeek(artistScrapRecord.getEndDateOnWeek())
                .artist(artistScrapRecord.getArtist())
                .build();
        store.put(autoIncrementId, persistArtistScrap);
        autoIncrementId++;
        return persistArtistScrap;
    }
}
