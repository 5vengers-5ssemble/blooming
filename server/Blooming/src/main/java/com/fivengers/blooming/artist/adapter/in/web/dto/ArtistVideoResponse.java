package com.fivengers.blooming.artist.adapter.in.web.dto;

import com.fivengers.blooming.artist.domain.ArtistVideo;
import java.util.List;

public record ArtistVideoResponse(String videoUrl) {

    public static ArtistVideoResponse from(ArtistVideo artistVideo) {
        return new ArtistVideoResponse(artistVideo.getVideoUrl());
    }
}
