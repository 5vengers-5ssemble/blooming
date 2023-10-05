package com.fivengers.blooming.live.adapter.in.web.dto;

import com.fivengers.blooming.live.domain.Live;
import lombok.Builder;

@Builder
public record LiveEnterInfoResponse(
        String liveTitle,
        String liveUserName,
        String sessionId,
        String motionModelUrl) {

    public static LiveEnterInfoResponse from(Live live, Long memberId) {
        return LiveEnterInfoResponse.builder()
                .liveTitle(live.getTitle())
                .liveUserName(live.getLiveUserName(memberId))
                .sessionId(live.getSessionId())
                .motionModelUrl(live.getMotionModelUrl())
                .build();
    }

}
