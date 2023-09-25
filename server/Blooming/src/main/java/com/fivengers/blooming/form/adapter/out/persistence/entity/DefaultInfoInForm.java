package com.fivengers.blooming.form.adapter.out.persistence.entity;


import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DefaultInfoInForm {

    private String image;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String title;

    @Builder
    public DefaultInfoInForm(String image, LocalDateTime startDate, LocalDateTime endDate, String title) {
        this.image = image;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
    }
}
