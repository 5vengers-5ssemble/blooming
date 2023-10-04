package com.fivengers.blooming.artist.adapter.in.web.dto;

import com.fivengers.blooming.artist.domain.Artist;
import lombok.Builder;

@Builder
public record ArtistListResponse(Long id,
                                 String stageName,
                                 String agency,
                                 String description) {

    public static ArtistListResponse from(Artist artist) {
        return ArtistListResponse.builder()
                .id(artist.getId())
                .stageName(artist.getStageName())
                .agency(artist.getAgency())
                .description(artist.getDescription())
                .build();
    }
}
