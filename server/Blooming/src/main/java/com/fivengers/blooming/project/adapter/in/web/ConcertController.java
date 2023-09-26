package com.fivengers.blooming.project.adapter.in.web;


import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.project.adapter.in.web.dto.ArtistResponse;
import com.fivengers.blooming.project.adapter.in.web.dto.ConcertDetailsResponse;
import com.fivengers.blooming.project.adapter.in.web.dto.ConcertListResponse;
import com.fivengers.blooming.project.adapter.in.web.dto.ConcertResponse;
import com.fivengers.blooming.project.adapter.in.web.dto.InvestmentResponse;
import com.fivengers.blooming.project.application.port.in.ConcertUseCase;
import com.fivengers.blooming.project.application.port.in.InvestmentOverviewUseCase;
import com.fivengers.blooming.project.application.port.in.ViewCountUseCase;
import com.fivengers.blooming.project.domain.Concert;
import jakarta.validation.constraints.Min;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/concerts")
@RequiredArgsConstructor
public class ConcertController {

    private final ConcertUseCase concertUseCase;
    private final InvestmentOverviewUseCase overviewUseCase;
    private final ViewCountUseCase viewCountUseCase;

    @GetMapping
    public ApiResponse<Page<ConcertListResponse>> concertList(Pageable pageable) {
        Page<Concert> concerts = concertUseCase.searchAll(pageable);
        return ApiResponse.ok(new PageImpl<>(concerts.stream()
                .map(ConcertListResponse::from)
                .toList(), pageable, concerts.getTotalElements()));
    }

    @GetMapping("/ongoing")
    public ApiResponse<Page<ConcertListResponse>> ongoingConcertList(Pageable pageable) {
        Page<Concert> concerts = concertUseCase.searchAllOngoingProject(pageable);
        return ApiResponse.ok(new PageImpl<>(concerts.stream()
                .map(ConcertListResponse::from)
                .toList(), pageable, concerts.getTotalElements()));
    }

    @GetMapping("/best")
    public ApiResponse<List<ConcertListResponse>> bestConcertList() {;
        return ApiResponse.ok(concertUseCase.searchBestThreeProject().stream()
                .map(ConcertListResponse::from)
                .toList());
    }

    @GetMapping("/{concertId}")
    public ApiResponse<ConcertDetailsResponse> concertDetails(
            @PathVariable @Min(1) Long concertId) {
        Concert concert = concertUseCase.searchById(concertId);
        return ApiResponse.ok(ConcertDetailsResponse.of(
                ArtistResponse.from(concert.getArtist()),
                ConcertResponse.from(concert),
                InvestmentResponse.of(overviewUseCase.search(concertId)),
                viewCountUseCase.searchWeeklyViewCount(concert)));
    }

    @GetMapping("/search/keyword")
    public ApiResponse<Page<ConcertListResponse>> concertListByLikeKeyword(
            @RequestParam String query, Pageable pageable) {
        Page<Concert> concerts = concertUseCase.searchAllByLikeKeyword(query, pageable);
        return ApiResponse.ok().body(
                new PageImpl<>(concerts.stream()
                        .map(ConcertListResponse::from)
                        .toList(), pageable, concerts.getTotalElements()));
    }

    @GetMapping("/search/artist")
    public ApiResponse<Page<ConcertListResponse>> concertListByLikeArtist(
            @RequestParam String query, Pageable pageable) {
        Page<Concert> concerts = concertUseCase.searchAllByLikeArtist(query, pageable);
        return ApiResponse.ok().body(
                new PageImpl<>(concerts.stream()
                        .map(ConcertListResponse::from)
                        .toList(), pageable, concerts.getTotalElements()));
    }
}
