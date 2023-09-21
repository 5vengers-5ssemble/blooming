package com.fivengers.blooming.project.adapter.in.web.dto;

import com.fivengers.blooming.artist.domain.Artist;
import lombok.Builder;

@Builder
public record ArtistResponse(Long id,
                             String profileImg,
                             String name,
                             String desc,
                             String youtubeUrl,
                             String fanCafeUrl,
                             String snsUrl) {

    public static ArtistResponse from(Artist artist) {
        return ArtistResponse.builder()
                .id(artist.getId())
                .profileImg(artist.getProfileImageUrl())
                .name(artist.getStageName())
                .desc(artist.getDescription())
                .youtubeUrl(artist.getYoutubeUrl())
                .fanCafeUrl(artist.getFanCafeUrl())
                .snsUrl(artist.getSnsUrl())
                .build();
    }
}