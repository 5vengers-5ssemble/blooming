package com.fivengers.blooming.live.adapter.in.web.dto;

import com.fivengers.blooming.live.domain.Live;

public record LiveEnterInfoResponse(
        String sessionId,
        String motionModelUrl) {

    public static LiveEnterInfoResponse from(Live live) {
        return new LiveEnterInfoResponse(live.getSessionId(), live.getMotionModelUrl());
    }

}
