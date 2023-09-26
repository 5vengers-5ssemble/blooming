package com.fivengers.blooming.project.adapter.in.web.dto;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.project.domain.Activity;
import com.fivengers.blooming.project.domain.InvestmentOverview;
import com.fivengers.blooming.project.domain.ViewCount;
import java.util.List;
import java.util.stream.IntStream;
import lombok.Builder;

@Builder
public record ActivityDetailsResponse(ArtistResponse artist,
                                      ActivityResponse concert,
                                      InvestmentResponse investment,
                                      List<Long> viewCounts) {

    public static ActivityDetailsResponse of(ArtistResponse artistResponse,
            ActivityResponse activityResponse,
            InvestmentResponse investmentResponse,
            List<ViewCount> viewCounts) {
        return ActivityDetailsResponse.builder()
                .artist(artistResponse)
                .concert(activityResponse)
                .investment(investmentResponse)
                .viewCounts(viewCounts.stream()
                        .map(ViewCount::getViewCount)
                        .toList())
                .build();
    }
}
