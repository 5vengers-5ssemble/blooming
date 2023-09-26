package com.fivengers.blooming.live.domain;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LiveFrequency {
    private LocalDate startDate;
    private LocalDate endDate;
    private int count;

    public static LiveFrequency of(LocalDate endDate, int count) {
        return new LiveFrequency(endDate.minusDays(6), endDate, count);

    }
}
