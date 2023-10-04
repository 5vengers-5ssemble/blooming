package com.fivengers.blooming.live.adapter.in.web.dto;

import com.fivengers.blooming.live.domain.LiveFrequency;
import java.time.LocalDate;

public record FrequencyInfoResponse(
        LocalDate startDate,
        LocalDate endDate,
        int count) {

    public static FrequencyInfoResponse from(LiveFrequency liveFrequency) {
        return new FrequencyInfoResponse(
                liveFrequency.getStartDate(),
                liveFrequency.getEndDate(),
                liveFrequency.getCount());
    }

}
