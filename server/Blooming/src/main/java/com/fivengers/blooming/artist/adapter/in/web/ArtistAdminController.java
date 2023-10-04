package com.fivengers.blooming.artist.adapter.in.web;

import com.fivengers.blooming.artist.adapter.in.web.dto.ArtistDetailsResponse;
import com.fivengers.blooming.artist.adapter.in.web.dto.ArtistVideoResponse;
import com.fivengers.blooming.artist.application.port.in.ArtistUseCase;
import com.fivengers.blooming.artist.application.port.in.ArtistVideoUseCase;
import com.fivengers.blooming.artist.application.port.in.dto.ArtistCreateRequest;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/artists")
@RequiredArgsConstructor
public class ArtistAdminController {

    private final ArtistUseCase artistUseCase;
    private final ArtistVideoUseCase artistVideoUseCase;

    @PostMapping
    public ApiResponse<ArtistDetailsResponse> artistCreate(@RequestBody
                                                           @Validated
                                                           ArtistCreateRequest request) {
        Artist artist = artistUseCase.add(request);

        return ApiResponse.ok(ArtistDetailsResponse.from(artist,
                ArtistVideoResponse.from(artistVideoUseCase.searchByArtistId(artist.getId()))));
    }

}
