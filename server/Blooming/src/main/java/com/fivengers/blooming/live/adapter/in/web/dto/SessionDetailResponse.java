package com.fivengers.blooming.live.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public record SessionDetailResponse (@NotBlank String sessionId) {

    public static SessionDetailResponse from(String sessionId) {
        return new SessionDetailResponse(sessionId);
    }
}
