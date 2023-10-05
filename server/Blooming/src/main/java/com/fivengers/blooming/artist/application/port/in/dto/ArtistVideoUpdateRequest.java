package com.fivengers.blooming.artist.application.port.in.dto;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.artist.domain.ArtistVideo;
import jakarta.validation.constraints.NotBlank;

public record ArtistVideoUpdateRequest(Long id,
                                       @NotBlank String videoUrl) {

    public boolean newVideo() {
        return this.id == null;
    }

    public ArtistVideo toDomain(Artist artist) {
        return ArtistVideo.builder()
                .videoUrl(videoUrl)
                .artist(artist)
                .build();
    }
}
