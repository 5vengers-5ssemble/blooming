package com.fivengers.blooming.live.adapter.in.web.dto;

import com.fivengers.blooming.artist.adapter.in.web.dto.ArtistLiveProfileResponse;
import com.fivengers.blooming.live.domain.Live;
import lombok.Builder;

@Builder
public record BestLiveListResponse(
        Long id,
        String title,

        String sessionId,
        String thumbnailUrl,
        int numberOfViewers,
        ArtistLiveProfileResponse artist) {

    public static BestLiveListResponse from(Live live) {
        return BestLiveListResponse.builder()
                .id(live.getId())
                .title(live.getTitle())
                .sessionId(live.getSessionId())
                .thumbnailUrl(live.getThumbnailUrl())
                .numberOfViewers(live.getNumberOfViewers())
                .artist(ArtistLiveProfileResponse.from(live.getArtist()))
                .build();
    }
}
