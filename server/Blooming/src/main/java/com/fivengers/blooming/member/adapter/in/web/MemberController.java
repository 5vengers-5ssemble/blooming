package com.fivengers.blooming.member.adapter.in.web;

import com.fivengers.blooming.artist.adapter.in.web.dto.ArtistListResponse;
import com.fivengers.blooming.artist.application.port.in.ArtistUseCase;
import com.fivengers.blooming.artistscrap.application.port.in.ArtistScrapUseCase;
import com.fivengers.blooming.config.security.oauth2.LoginUser;
import com.fivengers.blooming.global.response.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final ArtistScrapUseCase artistScrapUseCase;

    @GetMapping("/me/scrap-artists")
    public ApiResponse<List<ArtistListResponse>> getMyScrapArtistList(@AuthenticationPrincipal LoginUser loginUser) {
        return ApiResponse.ok(artistScrapUseCase.searchByMemberId(loginUser.getMemberId()).stream()
                .map(artistScrap -> ArtistListResponse.from(artistScrap.getArtist()))
                .toList());
    }
}
