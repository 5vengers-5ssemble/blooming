package com.fivengers.blooming.member.application.port.in.dto;

import jakarta.validation.constraints.NotBlank;

public record MemberTokenRefreshRequest(@NotBlank String refreshToken) {

    private static final String GRANT_TYPE = "Bearer ";

    public String refreshToken() {
        return refreshToken.replace(GRANT_TYPE, "");
    }
}
