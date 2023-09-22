package com.fivengers.blooming.project.adapter.in.web;


import com.fivengers.blooming.artist.application.port.in.ArtistUseCase;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.project.adapter.in.web.dto.ActivityDetailsResponse;
import com.fivengers.blooming.project.adapter.in.web.dto.ActivityListResponse;
import com.fivengers.blooming.project.adapter.in.web.dto.ConcertDetailsResponse;
import com.fivengers.blooming.project.adapter.in.web.dto.ConcertListResponse;
import com.fivengers.blooming.project.application.port.in.ActivityUseCase;
import com.fivengers.blooming.project.application.port.in.InvestmentOverviewUseCase;
import com.fivengers.blooming.project.application.port.in.ViewCountUseCase;
import com.fivengers.blooming.project.domain.Activity;
import com.fivengers.blooming.project.domain.InvestmentOverview;
import com.fivengers.blooming.project.domain.ViewCount;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityUseCase activityUseCase;
    private final ArtistUseCase artistUseCase;
    private final InvestmentOverviewUseCase overviewUseCase;
    private final ViewCountUseCase viewCountUseCase;

    @GetMapping
    public ApiResponse<Page<ActivityListResponse>> concertList(Pageable pageable) {
        Page<Activity> concerts = activityUseCase.searchAll(pageable);
        return ApiResponse.ok(new PageImpl<>(concerts.stream()
                .map(ActivityListResponse::from)
                .toList(), pageable, concerts.getTotalElements()));
    }

    @GetMapping("/ongoing")
    public ApiResponse<Page<ActivityListResponse>> ongoingConcertList(Pageable pageable) {
        Page<Activity> concerts = activityUseCase.searchAllOngoingProject(pageable);
        return ApiResponse.ok(new PageImpl<>(concerts.stream()
                .map(ActivityListResponse::from)
                .toList(), pageable, concerts.getTotalElements()));
    }

    @GetMapping("/{activityId}")
    public ApiResponse<ActivityDetailsResponse> concertDetails(@PathVariable Long activityId) {

        Activity activity = activityUseCase.searchById(activityId);
        Artist artist = artistUseCase.searchById(activity.getArtist().getId());
        InvestmentOverview overview = overviewUseCase.search(activityId);
        List<Activity> pastActivities = activityUseCase.searchAllFinishedProjectByArtist(artist);
        List<InvestmentOverview> pastOverviews = pastActivities.stream()
                .map(past -> overviewUseCase.search(past.getId()))
                .toList();
        List<ViewCount> viewCounts = viewCountUseCase.searchWeeklyViewCount(activity);
        return ApiResponse.ok(
                ActivityDetailsResponse.of(
                        artist, activity, overview, pastActivities, pastOverviews, viewCounts));
    }
}
