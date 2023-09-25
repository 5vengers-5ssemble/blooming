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
                                      List<PastActivityResponse> pastActivities,
                                      List<Long> viewCounts) {

    public static ActivityDetailsResponse of(Artist artist,
            Activity activity,
            InvestmentOverview overview,
            List<Activity> pastActivities,
            List<InvestmentOverview> pastOverviews,
            List<ViewCount> viewCounts) {
        return ActivityDetailsResponse.builder()
                .artist(ArtistResponse.from(artist))
                .concert(ActivityResponse.from(activity))
                .investment(InvestmentResponse.of(overview))
                .pastActivities(IntStream.range(0, pastActivities.size())
                        .mapToObj(idx -> PastActivityResponse.from(pastActivities.get(idx), pastOverviews.get(idx)))
                        .toList())
                .viewCounts(viewCounts.stream()
                        .map(ViewCount::getViewCount)
                        .toList())
                .build();
    }
}
