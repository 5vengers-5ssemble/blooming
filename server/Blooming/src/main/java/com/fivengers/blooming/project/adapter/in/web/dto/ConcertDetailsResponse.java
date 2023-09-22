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
                                     List<PastConcertResponse> pastConcerts,
                                     List<Long> viewCounts) {

    public static ConcertDetailsResponse of(Artist artist,
            Concert concert,
            InvestmentOverview overview,
            List<Concert> pastConcerts,
            List<InvestmentOverview> pastOverviews,
            List<ViewCount> viewCounts) {
        return ConcertDetailsResponse.builder()
                .artist(ArtistResponse.from(artist))
                .concert(ConcertResponse.from(concert))
                .investment(InvestmentResponse.of(overview))
                .pastConcerts(IntStream.range(0, pastConcerts.size())
                        .mapToObj(idx -> PastConcertResponse.from(pastConcerts.get(idx), pastOverviews.get(idx)))
                        .toList())
                .viewCounts(viewCounts.stream()
                        .map(ViewCount::getViewCount)
                        .toList())
                .build();
    }
}
