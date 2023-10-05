package com.fivengers.blooming.artist.adapter.in.web;

import com.fivengers.blooming.artist.adapter.in.web.dto.ArtistDetailsResponse;
import com.fivengers.blooming.artist.adapter.in.web.dto.ArtistListResponse;
import com.fivengers.blooming.artist.adapter.in.web.dto.ArtistVideoResponse;
import com.fivengers.blooming.artist.application.port.in.ArtistMembershipUseCase;
import com.fivengers.blooming.artist.application.port.in.ArtistUseCase;
import com.fivengers.blooming.artist.application.port.in.ArtistVideoUseCase;
import com.fivengers.blooming.artist.application.port.in.dto.ArtistModifyRequest;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.config.security.oauth2.LoginUser;
import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.membership.adapter.in.web.dto.MembershipDetailsResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/artists")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistUseCase artistUseCase;
    private final ArtistVideoUseCase artistVideoUseCase;
    private final ArtistMembershipUseCase artistMembershipUseCase;

    @GetMapping
    public ApiResponse<List<ArtistListResponse>> artistList() {
        return ApiResponse.ok(artistUseCase.searchAll().stream()
                .map(ArtistListResponse::from)
                .toList());
    }

    @GetMapping("/me")
    public ApiResponse<ArtistDetailsResponse> myArtistDetails(
            @AuthenticationPrincipal LoginUser loginUser) {
        Artist artist = artistUseCase.searchByMemberId(loginUser.getMemberId());
        return ApiResponse.ok(ArtistDetailsResponse.from(artist,
                artistVideoUseCase.searchByArtistId(artist.getId()).stream()
                        .map(ArtistVideoResponse::from)
                        .toList()));
    }

    @GetMapping("/{artistId}")
    public ApiResponse<ArtistDetailsResponse> artistDetails(@PathVariable Long artistId) {
        return ApiResponse.ok(ArtistDetailsResponse.from(
                artistUseCase.searchById(artistId),
                artistVideoUseCase.searchByArtistId(artistId).stream()
                        .map(ArtistVideoResponse::from)
                        .toList()));
    }

    @GetMapping("/{artistId}/memberships/ongoing")
    public ApiResponse<MembershipDetailsResponse> artistMembershipDetails(
            @PathVariable Long artistId) {
        return ApiResponse.ok(MembershipDetailsResponse.from(
                artistMembershipUseCase.searchOngoingByArtistId(artistId)));
    }

    @PutMapping("/{artistId}")
    public ApiResponse<ArtistDetailsResponse> artistModify(
            @PathVariable Long artistId,
            @RequestBody @Validated ArtistModifyRequest request,
            @AuthenticationPrincipal LoginUser loginUser) {
        return ApiResponse.ok(ArtistDetailsResponse.from(
                artistUseCase.modify(request, artistId, loginUser.getMemberId()),
                artistVideoUseCase.searchByArtistId(loginUser.getMemberId()).stream()
                        .map(ArtistVideoResponse::from)
                        .toList()));
    }
}
