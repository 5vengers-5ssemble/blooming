package com.fivengers.blooming.live.adapter.in.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record LiveFrequencyDetailsRequest(
        @NotNull @Min(1) Long artistId,
        @NotNull @Min(1) Integer numberOfWeeks) {

}
