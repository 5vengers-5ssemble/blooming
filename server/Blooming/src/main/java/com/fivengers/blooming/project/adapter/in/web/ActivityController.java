package com.fivengers.blooming.project.adapter.in.web;


import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.project.adapter.in.web.dto.ActivityDetailsResponse;
import com.fivengers.blooming.project.adapter.in.web.dto.ActivityListResponse;
import com.fivengers.blooming.project.adapter.in.web.dto.ActivityResponse;
import com.fivengers.blooming.project.adapter.in.web.dto.ArtistResponse;
import com.fivengers.blooming.project.adapter.in.web.dto.InvestmentResponse;
import com.fivengers.blooming.project.adapter.in.web.dto.InvestmentResponse.Overview;
import com.fivengers.blooming.project.adapter.in.web.dto.PastActivityResponse;
import com.fivengers.blooming.project.application.port.in.ActivityUseCase;
import com.fivengers.blooming.project.application.port.in.InvestmentOverviewUseCase;
import com.fivengers.blooming.project.application.port.in.ViewCountUseCase;
import com.fivengers.blooming.project.domain.Activity;
import com.fivengers.blooming.project.domain.InvestmentOverview;
import com.fivengers.blooming.project.domain.ViewCount;
import jakarta.validation.constraints.Min;
import java.util.List;
import java.util.stream.IntStream;
import lombok.Builder.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityUseCase activityUseCase;
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
    public ApiResponse<ActivityDetailsResponse> concertDetails(@PathVariable @Min(1) Long activityId) {
        Activity activity = activityUseCase.searchById(activityId);
        ArtistResponse artistResponse = ArtistResponse.from(activity.getArtist());
        ActivityResponse activityResponse = ActivityResponse.from(activity);
        InvestmentResponse investmentResponse = InvestmentResponse.of(overviewUseCase.search(activityId));
        List<ViewCount> viewCounts = viewCountUseCase.searchWeeklyViewCount(activity);
        return ApiResponse.ok(
                ActivityDetailsResponse.of(
                        artistResponse, activityResponse, investmentResponse, viewCounts));
    }

    @GetMapping("/{activityId}/histories")
    public ApiResponse<List<PastActivityResponse>> concertHistories(@PathVariable @Min(1) Long activityId) {
        Activity activity = activityUseCase.searchById(activityId);
        List<Activity> pastActivities = activityUseCase.searchAllFinishedProjectByArtist(activity.getArtist());
        List<InvestmentOverview> pastOverviews = pastActivities.stream()
                .map(past -> overviewUseCase.search(past.getId()))
                .toList();
        return ApiResponse.ok(IntStream.range(0, pastActivities.size())
                .mapToObj(idx -> PastActivityResponse.from(pastActivities.get(idx), pastOverviews.get(idx)))
                .toList());
    }

    @GetMapping("/search/keyword")
    public ApiResponse<Page<ActivityListResponse>> activityListByLikeKeyword(
            @RequestParam String query, Pageable pageable) {
        Page<Activity> activities = activityUseCase.searchAllByLikeKeyword(query, pageable);
        return ApiResponse.ok().body(
                new PageImpl<>(activities.stream()
                        .map(ActivityListResponse::from)
                        .toList(), pageable, activities.getTotalElements()));
    }

    @GetMapping("/search/artist")
    public ApiResponse<Page<ActivityListResponse>> activityListByLikeArtist(
            @RequestParam String query, Pageable pageable) {
        Page<Activity> activities = activityUseCase.searchAllByLikeArtist(query, pageable);
        return ApiResponse.ok().body(
                new PageImpl<>(activities.stream()
                        .map(ActivityListResponse::from)
                        .toList(), pageable, activities.getTotalElements()));
    }
}
