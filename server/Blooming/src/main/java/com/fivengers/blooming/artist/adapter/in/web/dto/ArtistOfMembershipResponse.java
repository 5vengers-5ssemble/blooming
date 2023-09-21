package com.fivengers.blooming.artist.adapter.in.web.dto;

import com.fivengers.blooming.artist.domain.Artist;
import lombok.Builder;

@Builder
public record ArtistOfMembershipResponse(String stageName) {

    public static ArtistOfMembershipResponse from(Artist artist) {
        return ArtistOfMembershipResponse.builder()
                .stageName(artist.getStageName())
                .build();
    }
}
