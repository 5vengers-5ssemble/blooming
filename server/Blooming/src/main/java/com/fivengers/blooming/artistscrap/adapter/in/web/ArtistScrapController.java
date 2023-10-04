package com.fivengers.blooming.artistscrap.adapter.in.web;

import com.fivengers.blooming.artistscrap.adapter.in.web.dto.ArtistScrapResponse;
import com.fivengers.blooming.artistscrap.application.port.in.ArtistScrapUseCase;
import com.fivengers.blooming.artistscrap.application.port.in.dto.ArtistScrapRequest;
import com.fivengers.blooming.config.security.oauth2.LoginUser;
import com.fivengers.blooming.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/artists/{artistId}")
@RequiredArgsConstructor
public class ArtistScrapController {

    private final ArtistScrapUseCase artistScrapUseCase;

    @GetMapping("/scrap")
    public ApiResponse<ArtistScrapResponse> scrapDetails(@PathVariable Long artistId,
            @AuthenticationPrincipal LoginUser loginUser) {
        return ApiResponse.ok(new ArtistScrapResponse(
                artistScrapUseCase.scraped(artistId, loginUser.getMemberId())));
    }

    @PostMapping("/scrap")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Object> scrapCreate(@PathVariable Long artistId,
            @RequestBody @Validated ArtistScrapRequest request) {
        artistScrapUseCase.scrap(request, artistId);
        return ApiResponse.noContent().build();
    }

    @PostMapping("/unscrap")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Object> scrapRemove(@PathVariable Long artistId,
            @RequestBody @Validated ArtistScrapRequest request) {
        artistScrapUseCase.unScrap(request, artistId);
        return ApiResponse.noContent().build();
    }
}
