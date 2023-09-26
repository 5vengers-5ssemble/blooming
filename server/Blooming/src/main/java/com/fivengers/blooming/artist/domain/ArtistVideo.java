package com.fivengers.blooming.artist.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ArtistVideo {

    private Long id;
    private String videoUrl;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Artist artist;

    @Builder
    public ArtistVideo(Long id,
                       String videoUrl,
                       LocalDateTime createdAt,
                       LocalDateTime modifiedAt,
                       Artist artist) {
        this.id = id;
        this.videoUrl = videoUrl;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.artist = artist;
    }
}
