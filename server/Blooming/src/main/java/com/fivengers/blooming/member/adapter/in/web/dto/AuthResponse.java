package com.fivengers.blooming.member.adapter.in.web.dto;

import com.fivengers.blooming.config.security.jwt.JwtToken;

public record AuthResponse(String accessToken,
                           String refreshToken,
                           MemberResponse member) {

    public static AuthResponse from(JwtToken jwtToken, MemberResponse memberResponse) {
        return new AuthResponse(jwtToken.getAccessToken(), jwtToken.getRefreshToken(),
                memberResponse);
    }
}
