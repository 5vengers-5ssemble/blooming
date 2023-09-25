package com.fivengers.blooming.artistscrap.adapter.in.web.dto;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.artistscrap.domain.ArtistScrapRecord;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record ArtistScrapRecordResponse(Integer scrapCount,
                                        LocalDateTime startDateOnWeek,
                                        LocalDateTime endDateOnWeek) {

    public static ArtistScrapRecordResponse from(ArtistScrapRecord artistScrapRecord) {
        return ArtistScrapRecordResponse.builder()
                .scrapCount(artistScrapRecord.getScrapCount())
                .startDateOnWeek(artistScrapRecord.getStartDateOnWeek())
                .endDateOnWeek(artistScrapRecord.getEndDateOnWeek())
                .build();
    }

}
