package com.fivengers.blooming.member.application.port.in.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest(@NotBlank String authToken) {

    private static final String GRANT_TYPE = "Bearer ";

    public String authToken() {
        return authToken.replace(GRANT_TYPE, "");
    }
}
