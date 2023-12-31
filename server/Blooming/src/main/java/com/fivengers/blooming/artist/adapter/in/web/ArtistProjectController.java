package com.fivengers.blooming.artist.adapter.in.web;

import com.fivengers.blooming.artist.adapter.in.web.dto.ArtistActivityResponse;
import com.fivengers.blooming.artist.adapter.in.web.dto.ArtistConcertResponse;
import com.fivengers.blooming.artist.adapter.in.web.dto.ArtistProjectListResponse;
import com.fivengers.blooming.artist.adapter.in.web.dto.ArtistProjectResponse;
import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.project.adapter.in.web.dto.PastActivityResponse;
import com.fivengers.blooming.project.adapter.in.web.dto.PastConcertResponse;
import com.fivengers.blooming.project.application.port.in.ActivityUseCase;
import com.fivengers.blooming.project.application.port.in.ConcertUseCase;
import com.fivengers.blooming.project.application.port.in.InvestmentOverviewUseCase;
import com.fivengers.blooming.project.application.port.in.ProjectUseCase;
import jakarta.validation.constraints.Min;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/artists/{artistId}")
@RequiredArgsConstructor
public class ArtistProjectController {

    private final ProjectUseCase projectUseCase;
    private final ActivityUseCase activityUseCase;
    private final ConcertUseCase concertUseCase;
    private final InvestmentOverviewUseCase overviewUseCase;

    @GetMapping("/activity/histories")
    public ApiResponse<List<PastActivityResponse>> activityHistories(@PathVariable @Min(1)
                                                                    Long artistId) {
        return ApiResponse.ok(activityUseCase.searchAllFinishedProjectByArtist(artistId).stream()
                .map(activity -> PastActivityResponse.from(
                        activity,
                        overviewUseCase.search(activity.getId())))
                .toList());
    }

    @GetMapping("/concert/histories")
    public ApiResponse<List<PastConcertResponse>> concertHistories(@PathVariable @Min(1)
                                                                   Long artistId) {
        return ApiResponse.ok(concertUseCase.searchAllFinishedProjectByArtist(artistId).stream()
                .map(concert -> PastConcertResponse.from(
                        concert,
                        overviewUseCase.search(concert.getId())))
                .toList());
    }

    @GetMapping("/projects")
    public ApiResponse<List<ArtistProjectListResponse>> projectList(@PathVariable @Min(1)
                                                                    Long artistId) {

        return ApiResponse.ok(projectUseCase.searchProjectsById(artistId).stream()
                .map(ArtistProjectListResponse::from)
                .toList());
    }

    @GetMapping("/activity/ongoing")
    public ApiResponse<ArtistActivityResponse> ongoingActivity(@PathVariable @Min(1) Long artistId) {

        return ApiResponse.ok(activityUseCase.searchByArtistId(artistId)
                .map(activity -> ArtistActivityResponse.from(ArtistProjectResponse.from(activity)))
                .orElse(ArtistActivityResponse.empty()));
    }

    @GetMapping("/concert/ongoing")
    public ApiResponse<ArtistConcertResponse> ongoingConcert(@PathVariable @Min(1) Long artistId) {

        return ApiResponse.ok(concertUseCase.searchByArtistId(artistId)
                .map(concert -> ArtistConcertResponse.from(ArtistProjectResponse.from(concert)))
                .orElse(ArtistConcertResponse.empty()));
    }
}
