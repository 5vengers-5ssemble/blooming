package com.fivengers.blooming.emoji.domain;

import com.fivengers.blooming.artist.domain.Artist;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class Emoji {

    private Long id;
    private String name;
    private int code;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Artist artist;

    @Builder
    public Emoji(Long id, String name, int code, LocalDateTime createdAt, LocalDateTime modifiedAt,
            Artist artist) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.artist = artist;
    }
}
