package com.fivengers.blooming.artist.adapter.in.web.dto;

import com.fivengers.blooming.artist.domain.ArtistVideo;
import java.util.List;

public record ArtistVideoResponse(List<String> videoUrl) {

    public static ArtistVideoResponse from(List<ArtistVideo> artistVideos) {
        return new ArtistVideoResponse(artistVideos.stream()
                .map(ArtistVideo::getVideoUrl)
                .toList());
    }
}