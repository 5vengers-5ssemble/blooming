package com.fivengers.blooming.project.adapter.in.web.dto;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.project.domain.Concert;
import com.fivengers.blooming.project.domain.InvestmentOverview;
import com.fivengers.blooming.project.domain.ViewCount;
import java.util.List;
import java.util.stream.IntStream;
import lombok.Builder;

@Builder
public record ConcertDetailsResponse(ArtistResponse artist,
                                     ConcertResponse concert,
                                     InvestmentResponse investment,
                                     List<Long> viewCounts) {

    public static ConcertDetailsResponse of(ArtistResponse artistResponse,
            ConcertResponse concertResponse,
            InvestmentResponse investmentResponse,
            List<ViewCount> viewCounts) {
        return ConcertDetailsResponse.builder()
                .artist(artistResponse)
                .concert(concertResponse)
                .investment(investmentResponse)
                .viewCounts(viewCounts.stream()
                        .map(ViewCount::getViewCount)
                        .toList())
                .build();
    }
}
