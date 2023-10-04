package com.fivengers.blooming.artist.application.port.in.dto;

import com.fivengers.blooming.artist.domain.Artist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ArtistModifyRequest(@NotNull Long artistId,
                                  @NotBlank String stageName,
                                  @NotBlank String agency,
                                  @NotNull String description,
                                  @NotNull String profileImageUrl,
                                  @NotNull String youtubeUrl,
                                  @NotNull String fanCafeUrl,
                                  @NotNull String snsUrl) {

    public Artist toDomain() {
        return Artist.builder()
                .stageName(stageName)
                .agency(agency)
                .description(description)
                .profileImageUrl(profileImageUrl)
                .youtubeUrl(youtubeUrl)
                .fanCafeUrl(fanCafeUrl)
                .snsUrl(snsUrl)
                .build();
    }
}
