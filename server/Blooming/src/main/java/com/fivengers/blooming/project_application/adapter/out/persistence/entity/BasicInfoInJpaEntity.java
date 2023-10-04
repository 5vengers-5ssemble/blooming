package com.fivengers.blooming.project_application.adapter.out.persistence.entity;


import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BasicInfoInJpaEntity {

    private String title;
    private String thumbnail;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Builder
    public BasicInfoInJpaEntity(String title, String thumbnail, LocalDateTime startDate,
            LocalDateTime endDate) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
