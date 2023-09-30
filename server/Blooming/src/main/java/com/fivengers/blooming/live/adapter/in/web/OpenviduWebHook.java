package com.fivengers.blooming.live.adapter.in.web;

import com.fivengers.blooming.global.exception.live.OpenviduWebHookNotFoundException;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum OpenviduWebHook {
    PARTICIPANT_JOINED("participantJoined"),
    PARTICIPANT_LEFT("participantLeft");

    private final String event;

    public static OpenviduWebHook from(String event) {
        return Arrays.stream(OpenviduWebHook.values())
                .filter((webhook) -> webhook.getEvent().equals(event))
                .findAny()
                .orElseThrow(OpenviduWebHookNotFoundException::new);
    }

    OpenviduWebHook(String event) {
        this.event = event;
    }
}
