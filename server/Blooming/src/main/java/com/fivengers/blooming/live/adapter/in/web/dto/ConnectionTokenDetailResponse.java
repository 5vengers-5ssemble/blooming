package com.fivengers.blooming.live.adapter.in.web.dto;

public record ConnectionTokenDetailResponse (String token) {
    public static ConnectionTokenDetailResponse from(String token) {
        return new ConnectionTokenDetailResponse(token);
    }
}
