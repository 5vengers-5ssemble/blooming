package com.fivengers.blooming.project.adapter.in.web.dto;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.project.domain.Concert;
import com.fivengers.blooming.project.domain.InvestmentOverview;
import com.fivengers.blooming.project.domain.ViewCount;
import java.util.List;
import lombok.Builder;

@Builder
public record ConcertDetailsResponse(ArtistResponse artist,
                                     ConcertResponse concert,
                                     InvestmentResponse investment,
                                     List<PastConcertResponse> pastConcerts,
                                     List<Long> viewCounts) {

    public static ConcertDetailsResponse of(Artist artist,
            Concert concert,
            InvestmentOverview overview,
            List<Concert> pastConcerts,
            List<ViewCount> viewCounts) {
        return ConcertDetailsResponse.builder()
                .artist(ArtistResponse.from(artist))
                .concert(ConcertResponse.from(concert))
                .investment(InvestmentResponse.of(overview))
                .pastConcerts(pastConcerts.stream()
                        .map(PastConcertResponse::from)
                        .toList())
                .viewCounts(viewCounts.stream()
                        .map(ViewCount::getViewCount)
                        .toList())
                .build();
    }
}
