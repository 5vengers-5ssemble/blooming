package com.fivengers.blooming.artist.adapter.in.web.dto;

import com.fivengers.blooming.artist.domain.Artist;
import lombok.Builder;

@Builder
public record ArtistDetailsResponse(String stageName,
                                    String agency,
                                    String description,
                                    String profileImageUrl) {

    public static ArtistDetailsResponse from(Artist artist) {
        return ArtistDetailsResponse.builder()
                .stageName(artist.getStageName())
                .agency(artist.getAgency())
                .description(artist.getDescription())
                .profileImageUrl(artist.getProfileImageUrl())
                .build();
    }
}
