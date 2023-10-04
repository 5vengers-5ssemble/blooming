package com.fivengers.blooming.live.adapter.in.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LiveCreateRequest(
        @NotBlank String liveTitle,
        @NotNull @Min(1) Long artistId,
        String thumbnailUrl) {

}
