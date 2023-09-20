package com.fivengers.blooming.live.domain;

import com.fivengers.blooming.artist.domain.Artist;
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
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime endedAt;
    private LocalDateTime modifiedAt;
    private Artist artist;

    @Builder
    public Live(
            Long id,
            String title,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            LocalDateTime endedAt,
            Artist artist) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.endedAt = endedAt;
        this.artist = artist;
    }

    public String getSessionId() {
        return SESSION_PREFIX + id;
    }
}
