package com.fivengers.blooming.member.adapter.in.web;

import com.fivengers.blooming.artist.adapter.in.web.dto.ArtistListResponse;
import com.fivengers.blooming.artist.application.port.in.ArtistUseCase;
import com.fivengers.blooming.artistscrap.application.port.in.ArtistScrapUseCase;
import com.fivengers.blooming.config.security.oauth2.LoginUser;
import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.member.adapter.in.web.dto.MemberDetailsResponse;
import com.fivengers.blooming.member.adapter.in.web.dto.MemberResponse;
import com.fivengers.blooming.member.application.port.in.MemberUseCase;
import com.fivengers.blooming.member.application.port.in.dto.MemberModifyRequest;
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
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final ArtistScrapUseCase artistScrapUseCase;
    private final MemberUseCase memberUseCase;

    @GetMapping("/me/scrap-artists")
    public ApiResponse<List<ArtistListResponse>> myScrapArtistList(
            @AuthenticationPrincipal LoginUser loginUser) {
        return ApiResponse.ok(artistScrapUseCase.searchByMemberId(loginUser.getMemberId()).stream()
                .map(artistScrap -> ArtistListResponse.from(artistScrap.getArtist()))
                .toList());
    }

    @PutMapping("/{memberId}")
    public ApiResponse<MemberDetailsResponse> memberModify(
            @PathVariable Long memberId,
            @RequestBody @Validated MemberModifyRequest request,
            @AuthenticationPrincipal LoginUser loginUser) {

        return ApiResponse.ok(MemberDetailsResponse.from(
                memberUseCase.modify(request, memberId, loginUser.getMemberId())));
    }
}
