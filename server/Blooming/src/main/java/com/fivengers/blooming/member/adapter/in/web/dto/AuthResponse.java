package com.fivengers.blooming.member.adapter.in.web.dto;

import com.fivengers.blooming.config.security.jwt.JwtToken;

public record AuthResponse(String accessToken,
                           String refreshToken) {

    public static AuthResponse from(JwtToken jwtToken) {
        return new AuthResponse(jwtToken.getAccessToken(), jwtToken.getRefreshToken());
    }
}
