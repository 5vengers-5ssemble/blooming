package com.fivengers.blooming.membership.adapter.in.web;

import com.fivengers.blooming.config.security.oauth2.LoginUser;
import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.membership.adapter.in.web.dto.MembershipApplicationDetailsResponse;
import com.fivengers.blooming.membership.application.port.in.MembershipApplicationUseCase;
import com.fivengers.blooming.membership.application.port.in.dto.MembershipApplyRequest;
import com.fivengers.blooming.membership.domain.MembershipApplication;
import com.fivengers.blooming.membership.domain.MembershipApplicationState;
import com.fivengers.blooming.project.adapter.in.web.dto.ArtistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/membership-applications")
@RequiredArgsConstructor
public class MembershipApplicationController {

    private final MembershipApplicationUseCase membershipApplicationUseCase;

    @PostMapping
    public ApiResponse<MembershipApplicationDetailsResponse> membershipApplicationCreate(
            @RequestBody @Validated MembershipApplyRequest request,
            @AuthenticationPrincipal LoginUser loginUser) {
        MembershipApplication membershipApplication = membershipApplicationUseCase.add(request,
                loginUser.getMemberId());

        return ApiResponse.ok(MembershipApplicationDetailsResponse.from(membershipApplication,
                ArtistResponse.from(membershipApplication.getArtist())));
    }

    @GetMapping("/me")
    public ApiResponse<MembershipApplicationDetailsResponse> membershipApplicationMyDetails(
            @AuthenticationPrincipal LoginUser loginUser,
            @RequestParam MembershipApplicationState state) {
        MembershipApplication membershipApplication = membershipApplicationUseCase.searchByMemberIdAndApplicationState(
                loginUser.getMemberId(), state);

        return ApiResponse.ok(MembershipApplicationDetailsResponse.from(membershipApplication,
                ArtistResponse.from(membershipApplication.getArtist())));
    }
}
