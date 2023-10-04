package com.fivengers.blooming.live.adapter.in.web.dto;

import com.fivengers.blooming.live.domain.LiveFrequency;
import java.util.List;
import lombok.Getter;

public record LiveFrequencyDetailsResponse(
        Long artistId,
        List<FrequencyInfoResponse> frequencies

) {
    public static LiveFrequencyDetailsResponse from(Long artistId, List<FrequencyInfoResponse> liveFrequencies) {
        return new LiveFrequencyDetailsResponse(
                artistId,
                liveFrequencies
        );
    }

}
