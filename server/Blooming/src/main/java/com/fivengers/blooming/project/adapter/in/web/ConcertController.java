package com.fivengers.blooming.project.adapter.in.web;


import com.fivengers.blooming.artist.application.port.in.ArtistUseCase;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.project.adapter.in.web.dto.ArtistResponse;
import com.fivengers.blooming.project.adapter.in.web.dto.ConcertDetailsResponse;
import com.fivengers.blooming.project.adapter.in.web.dto.ConcertListResponse;
import com.fivengers.blooming.project.adapter.in.web.dto.ConcertResponse;
import com.fivengers.blooming.project.adapter.in.web.dto.InvestmentResponse;
import com.fivengers.blooming.project.adapter.in.web.dto.InvestmentResponse.Overview;
import com.fivengers.blooming.project.adapter.in.web.dto.PastConcertResponse;
import com.fivengers.blooming.project.application.port.in.ConcertUseCase;
import com.fivengers.blooming.project.application.port.in.InvestmentOverviewUseCase;
import com.fivengers.blooming.project.application.port.in.ViewCountUseCase;
import com.fivengers.blooming.project.domain.Concert;
import com.fivengers.blooming.project.domain.InvestmentOverview;
import com.fivengers.blooming.project.domain.ViewCount;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/concerts")
@RequiredArgsConstructor
public class ConcertController {

    private final ConcertUseCase concertUseCase;
    private final ArtistUseCase artistUseCase;
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

    @GetMapping("/{concertId}")
    public ApiResponse<ConcertDetailsResponse> concertDetails(@PathVariable Long concertId) {

        Concert concert = concertUseCase.searchById(concertId);
        Artist artist = artistUseCase.searchById(concert.getArtist().getId());
        InvestmentOverview overview = overviewUseCase.search(concertId);
        List<Concert> pastConcerts = concertUseCase.searchAllFinishedProjectByArtist(artist);
        List<InvestmentOverview> pastOverviews = pastConcerts.stream()
                .map(past -> overviewUseCase.search(past.getId()))
                .toList();
        List<ViewCount> viewCounts = viewCountUseCase.searchWeeklyViewCount(concert);
        return ApiResponse.ok(
                ConcertDetailsResponse.of(
                        artist, concert, overview, pastConcerts, pastOverviews, viewCounts));
    }
}
