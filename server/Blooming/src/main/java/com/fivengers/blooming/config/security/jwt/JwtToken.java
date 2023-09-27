package com.fivengers.blooming.config.security.jwt;

import lombok.Getter;

@Getter
public class JwtToken {

    private String accessToken;
    private String refreshToken;
    private String grantType;

    public JwtToken(String accessToken, String refreshToken, String grantType) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.grantType = grantType;
    }
}
