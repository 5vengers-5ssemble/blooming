package com.fivengers.blooming.artist.adapter.in.web;

import com.fivengers.blooming.artist.adapter.in.web.dto.ArtistDetailsResponse;
import com.fivengers.blooming.artist.adapter.in.web.dto.ArtistListResponse;
import com.fivengers.blooming.artist.adapter.in.web.dto.ArtistVideoResponse;
import com.fivengers.blooming.artist.application.port.in.ArtistUseCase;
import com.fivengers.blooming.artist.application.port.in.ArtistVideoUseCase;
import com.fivengers.blooming.artist.application.port.in.dto.ArtistCreateRequest;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.artist.domain.ArtistVideo;
import com.fivengers.blooming.global.response.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/artists")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistUseCase artistUseCase;
    private final ArtistVideoUseCase artistVideoUseCase;

    @GetMapping
    public ApiResponse<List<ArtistListResponse>> artistList() {
        return ApiResponse.ok(artistUseCase.searchAll().stream()
                .map(ArtistListResponse::from)
                .toList());
    }

    @GetMapping("/{artistId}")
    public ApiResponse<ArtistDetailsResponse> artistDetails(@PathVariable Long artistId) {
        return ApiResponse.ok(ArtistDetailsResponse.from(
                artistUseCase.searchById(artistId),
                artistVideoUseCase.searchByArtistId(artistId).stream()
                        .map(ArtistVideoResponse::from)
                        .toList()));
    }

    @PostMapping
    public ApiResponse<ArtistDetailsResponse> artistCreate(@RequestBody
                                                           @Validated
                                                           ArtistCreateRequest request,
                                                           @RequestParam Long memberId) {
        Artist artist = artistUseCase.add(request, memberId);
        return ApiResponse.ok(ArtistDetailsResponse.from(artist,
                artistVideoUseCase.searchByArtistId(artist.getId()).stream()
                        .map(ArtistVideoResponse::from)
                        .toList()));
    }
}
