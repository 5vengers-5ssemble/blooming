package com.fivengers.blooming.live.adapter.in.web.dto;

import com.fivengers.blooming.artist.adapter.in.web.dto.ArtistLiveProfileResponse;
import com.fivengers.blooming.live.domain.Live;
import lombok.Builder;

@Builder
public record LiveListResponse(
        Long id,
        ArtistLiveProfileResponse artist,
        String title,
        String sessionId
) {

    public static LiveListResponse from(Live live) {
        return LiveListResponse.builder()
                .id(live.getId())
                .artist(ArtistLiveProfileResponse.from(live.getArtist()))
                .title(live.getTitle())
                .sessionId(live.getSessionId())
                .build();
    }
}
