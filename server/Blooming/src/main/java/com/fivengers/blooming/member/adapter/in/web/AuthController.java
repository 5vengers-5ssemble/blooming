package com.fivengers.blooming.member.adapter.in.web;

import com.fivengers.blooming.artist.application.port.in.ArtistUseCase;
import com.fivengers.blooming.config.security.jwt.JwtProvider;
import com.fivengers.blooming.config.security.jwt.JwtToken;
import com.fivengers.blooming.config.security.jwt.JwtValidator;
import com.fivengers.blooming.config.security.oauth2.LoginUser;
import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.member.adapter.in.web.dto.AuthResponse;
import com.fivengers.blooming.member.adapter.in.web.dto.MemberResponse;
import com.fivengers.blooming.member.application.port.in.dto.AuthRequest;
import com.fivengers.blooming.member.domain.Member;
import com.fivengers.blooming.member.domain.MemberRole;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtProvider jwtProvider;
    private final JwtValidator jwtValidator;
    private final ArtistUseCase artistUseCase;

    @PostMapping
    public ApiResponse<AuthResponse> authTokenDetails(@RequestBody
                                                      @Validated
                                                      AuthRequest authRequest) {
        LoginUser loginUser = jwtValidator.findLoginUserByToken(authRequest.authToken());
        Member member = loginUser.getMember();

        return ApiResponse.ok(AuthResponse.from(jwtProvider.createJwtToken(loginUser),
                MemberResponse.from(findIdentitySuitRole(member), member)));
    }

    private Long findIdentitySuitRole(Member member) {
        if (member.isArtist()) {
            return artistUseCase.searchByMemberId(member.getId()).getId();
        }

        return member.getId();
    }
}
