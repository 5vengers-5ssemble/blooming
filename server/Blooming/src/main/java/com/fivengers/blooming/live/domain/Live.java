package com.fivengers.blooming.live.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class Live {

    private static final String SESSION_PREFIX = "blooming";

    private Long id;
    private Artist artist;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime endedAt;
    private LocalDateTime modifiedAt;

    @Builder
    public Live(
            Long id,
            Artist artist,
            String title,
            LocalDateTime createdAt,
            LocalDateTime endedAt,
            LocalDateTime modifiedAt) {
        this.id = id;
        this.artist = artist;
        this.title = title;
        this.createdAt = createdAt;
        this.endedAt = endedAt;
        this.modifiedAt = modifiedAt;
    }

    public String getSessionId() {
        return SESSION_PREFIX + id;
    }
}
