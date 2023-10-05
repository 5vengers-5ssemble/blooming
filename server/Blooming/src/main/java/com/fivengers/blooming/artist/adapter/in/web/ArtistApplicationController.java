package com.fivengers.blooming.artist.adapter.in.web;

import com.fivengers.blooming.artist.adapter.in.web.dto.ArtistApplicationDetailsResponse;
import com.fivengers.blooming.artist.application.port.in.ArtistApplicationUseCase;
import com.fivengers.blooming.artist.application.port.in.dto.ArtistApplyRequest;
import com.fivengers.blooming.artist.domain.ArtistApplication;
import com.fivengers.blooming.config.security.oauth2.LoginUser;
import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.member.adapter.in.web.dto.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/artist-applications")
@RequiredArgsConstructor
public class ArtistApplicationController {

    private final ArtistApplicationUseCase artistApplicationUseCase;

    @PostMapping
    public ApiResponse<ArtistApplicationDetailsResponse> artistApplicationCreate(
            @RequestBody @Validated ArtistApplyRequest request,
            @AuthenticationPrincipal LoginUser loginUser) {
        ArtistApplication artistApplication =
                artistApplicationUseCase.add(request, loginUser.getMemberId());

        return ApiResponse.ok(ArtistApplicationDetailsResponse.from(artistApplication,
                MemberResponse.from(artistApplication.getMember().getId(),
                        artistApplication.getMember())));
    }

    @GetMapping("/me")
    public ApiResponse<ArtistApplicationDetailsResponse> artistApplicationMyDetails(
            @AuthenticationPrincipal LoginUser loginUser) {
        ArtistApplication artistApplication =
                artistApplicationUseCase.searchByMemberId(loginUser.getMemberId());

        return ApiResponse.ok(ArtistApplicationDetailsResponse.from(artistApplication,
                MemberResponse.from(artistApplication.getMember().getId(),
                        artistApplication.getMember())));
    }
}
