package com.fivengers.blooming.live.adapter.in.web.dto;

import java.sql.Timestamp;

public record OpenviduWebhookRequest(
        String event,
        Timestamp timestamp,
        String sessionId,
        Timestamp startTime,
        Integer duration,
        String reason,
        String ip,
        String platform,
        String clientData
) {

}
