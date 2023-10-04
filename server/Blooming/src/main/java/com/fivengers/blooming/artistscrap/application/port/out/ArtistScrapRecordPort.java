package com.fivengers.blooming.artistscrap.application.port.out;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.artistscrap.domain.ArtistScrapRecord;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ArtistScrapRecordPort {

    ArtistScrapRecord save(ArtistScrapRecord artistScrapRecord);

    List<ArtistScrapRecord> findTopByArtistIdOrderByStartDateDesc(Long artistId, Long limit);

    Optional<ArtistScrapRecord> findOnWeek(LocalDateTime startDate, LocalDateTime endDate,
            Artist artist);

    void update(ArtistScrapRecord artistScrapRecord);
}
