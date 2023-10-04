package com.fivengers.blooming.emoji.domain;

import com.fivengers.blooming.artist.domain.Artist;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MotionModel {

    private Long id;
    private String modelUrl;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Artist artist;

    @Builder
    public MotionModel(Long id, String modelUrl, LocalDateTime createdAt, LocalDateTime modifiedAt,
            Artist artist) {
        this.id = id;
        this.modelUrl = modelUrl;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.artist = artist;
    }
}
