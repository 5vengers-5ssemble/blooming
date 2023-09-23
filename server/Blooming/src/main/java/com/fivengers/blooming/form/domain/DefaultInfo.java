package com.fivengers.blooming.form.domain;


import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DefaultInfo {

    private String image;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String title;

    @Builder
    public DefaultInfo(String image, LocalDateTime startDate, LocalDateTime endDate, String title) {
        this.image = image;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
    }
}
