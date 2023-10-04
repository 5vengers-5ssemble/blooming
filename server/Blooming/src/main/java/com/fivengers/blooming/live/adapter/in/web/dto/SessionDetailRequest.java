package com.fivengers.blooming.live.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.Map;

public record SessionDetailRequest(@NotBlank String customSessionId) {
    public Map<String, String> toMap() {
        return Map.of("customSessionId", customSessionId);
    }

}
