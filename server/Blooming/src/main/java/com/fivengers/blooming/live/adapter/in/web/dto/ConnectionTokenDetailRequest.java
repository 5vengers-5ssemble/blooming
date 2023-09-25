package com.fivengers.blooming.live.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public record ConnectionTokenDetailRequest(@NotBlank String sessionId) {
    // TODO : sessionId 규칙 검증 ValidationAnnotation 작성

}
