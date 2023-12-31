package com.fivengers.blooming.artist.adapter.in.web.dto;

import com.fivengers.blooming.artist.domain.Artist;
import java.util.List;
import lombok.Builder;

@Builder
public record ArtistDetailsResponse(Long id,
                                    String stageName,
                                    String agency,
                                    String description,
                                    String profileImageUrl,
                                    String youtubeUrl,
                                    String fanCafeUrl,
                                    String snsUrl,
                                    List<ArtistVideoResponse> artistVideo) {

    public static ArtistDetailsResponse from(Artist artist, List<ArtistVideoResponse> artistVideo) {
        return ArtistDetailsResponse.builder()
                .id(artist.getId())
                .stageName(artist.getStageName())
                .agency(artist.getAgency())
                .description(artist.getDescription())
                .profileImageUrl(artist.getProfileImageUrl())
                .youtubeUrl(artist.getYoutubeUrl())
                .fanCafeUrl(artist.getFanCafeUrl())
                .snsUrl(artist.getSnsUrl())
                .artistVideo(artistVideo)
                .build();
    }
}
