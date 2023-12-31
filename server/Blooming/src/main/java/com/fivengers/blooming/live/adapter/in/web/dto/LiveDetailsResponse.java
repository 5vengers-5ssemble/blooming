package com.fivengers.blooming.live.adapter.in.web.dto;

import com.fivengers.blooming.artist.adapter.in.web.dto.ArtistLiveProfileResponse;
import com.fivengers.blooming.live.domain.Live;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record LiveDetailsResponse(Long id,
                                  String title,
                                  String thumbnailUrl,
                                  String motionModelUrl,
                                  String sessionId,
                                  LocalDateTime createdAt,
                                  LocalDateTime endedAt,
                                  LocalDateTime modifiedAt,
                                  ArtistLiveProfileResponse artist) {

    public static LiveDetailsResponse from(Live live) {
        return LiveDetailsResponse.builder()
                .id(live.getId())
                .title(live.getTitle())
                .thumbnailUrl(live.getThumbnailUrl())
                .motionModelUrl(live.getMotionModelUrl())
                .sessionId(live.getSessionId())
                .createdAt(live.getCreatedAt())
                .endedAt(live.getEndedAt())
                .modifiedAt(live.getModifiedAt())
                .artist(ArtistLiveProfileResponse.from(live.getArtist()))
                .build();
    }

}