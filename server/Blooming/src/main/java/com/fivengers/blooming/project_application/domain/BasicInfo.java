package com.fivengers.blooming.project_application.domain;


import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BasicInfo {

    private String title;
    private String thumbnail;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Builder
    public BasicInfo(String title,
            String thumbnail,
            LocalDateTime startDate,
            LocalDateTime endDate) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
