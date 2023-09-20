package com.fivengers.blooming.artist.adapter.in.web.dto;

import com.fivengers.blooming.artist.domain.Artist;
import lombok.Builder;

@Builder
public record ArtistLiveProfileResponse(Long id,
                                        String stageName,
                                        String profileImageUrl) {

    public static ArtistLiveProfileResponse from(Artist artist) {
        return ArtistLiveProfileResponse.builder()
                .id(artist.getId())
                .stageName(artist.getStageName())
                .profileImageUrl(artist.getProfileImageUrl())
                .build();
    }
}
