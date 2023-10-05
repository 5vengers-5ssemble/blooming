package com.fivengers.blooming.member.adapter.in.web;

import com.fivengers.blooming.artist.application.port.in.ArtistUseCase;
import com.fivengers.blooming.config.security.jwt.JwtToken;
import com.fivengers.blooming.config.security.jwt.JwtValidator;
import com.fivengers.blooming.config.security.oauth2.LoginUser;
import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.member.adapter.in.web.dto.AuthResponse;
import com.fivengers.blooming.member.adapter.in.web.dto.MemberResponse;
import com.fivengers.blooming.member.application.port.in.MemberTokenUseCase;
import com.fivengers.blooming.member.application.port.in.dto.AuthRequest;
import com.fivengers.blooming.member.application.port.in.dto.MemberTokenRefreshRequest;
import com.fivengers.blooming.member.domain.Member;
import com.fivengers.blooming.member.domain.MemberToken;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtValidator jwtValidator;
    private final ArtistUseCase artistUseCase;
    private final MemberTokenUseCase memberTokenUseCase;

    @PostMapping
    public ApiResponse<AuthResponse> authTokenDetails(
            @RequestBody @Validated AuthRequest authRequest) {
        LoginUser loginUser = jwtValidator.findLoginUserByToken(authRequest.authToken());

        JwtToken jwtToken = memberTokenUseCase.createJwtToken(loginUser);
        return ApiResponse.ok(AuthResponse.from(jwtToken,
                MemberResponse.from(getArtistId(loginUser.getMember()), loginUser.getMember())));
    }

    @PostMapping("/refresh")
    public ApiResponse<AuthResponse> refreshToken(
            @RequestBody @Validated MemberTokenRefreshRequest request) {
        LoginUser loginUser = jwtValidator.findLoginUserByToken(request.refreshToken());
        if (loginUser != null) {
            return ApiResponse.ok(AuthResponse.from(memberTokenUseCase.createJwtToken(loginUser),
                    MemberResponse.from(getArtistId(loginUser.getMember()), loginUser.getMember())));
        }

        MemberToken memberToken = memberTokenUseCase.findByRefreshToken(request.refreshToken());
        return ApiResponse.ok(AuthResponse.from(memberTokenUseCase.refresh(request, memberToken),
                MemberResponse.from(getArtistId(memberToken.getMember()), memberToken.getMember())));
    }

    private Long getArtistId(Member member) {
        if (member.isArtist()) {
            return artistUseCase.searchByMemberId(member.getId()).getId();
        }

        return null;
    }
}
