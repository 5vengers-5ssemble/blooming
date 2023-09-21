package com.fivengers.blooming.artistscrap.adapter.in.web;

import com.fivengers.blooming.artistscrap.application.port.in.ArtistScrapUseCase;
import com.fivengers.blooming.artistscrap.application.port.in.dto.ArtistScrapRequest;
import com.fivengers.blooming.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/artists/{artistId}")
@RequiredArgsConstructor
public class ArtistScrapController {

    private final ArtistScrapUseCase artistScrapUseCase;

    @PostMapping("/scrap")
    public ApiResponse<Object> scrapCreate(@PathVariable Long artistId,
                                           @RequestBody @Validated ArtistScrapRequest request) {
        artistScrapUseCase.scrap(request, artistId);
        return ApiResponse.noContent().build();
    }

    @PostMapping("/unscrap")
    public ApiResponse<Object> scrapRemove(@PathVariable Long artistId,
                                           @RequestBody @Validated ArtistScrapRequest request) {
        artistScrapUseCase.unScrap(request, artistId);
        return ApiResponse.noContent().build();
    }
}
