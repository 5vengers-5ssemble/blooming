package com.fivengers.blooming.live.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public record ConnectionTokenDetailRequest(@NotBlank String sessionId) {

}
