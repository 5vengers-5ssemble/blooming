package com.fivengers.blooming.artist.application.port.in.dto;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.artist.domain.ArtistVideo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ArtistVideoUpdateRequest(@NotNull Long id,
                                       @NotBlank String videoUrl) {

    public boolean isNew() {
        return this.id == null;
    }

    public ArtistVideo toDomain(Artist artist) {
        return ArtistVideo.builder()
                .videoUrl(videoUrl)
                .artist(artist)
                .build();
    }
}
