package com.fivengers.blooming.artist.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fivengers.blooming.global.exception.artist.InvalidArtistApplicationStateException;
import lombok.Getter;

@Getter
public enum ArtistApplicationState {
    APPLY("신청"),
    APPROVAL("승인"),
    RETURN("거절"),
    CANCEL("취소");

    private final String value;

    ArtistApplicationState(String value) {
        this.value = value;
    }

    @JsonCreator
    public ArtistApplicationState from(String value) {
        for (ArtistApplicationState state : ArtistApplicationState.values()) {
            if (state.getValue().equals(value)) {
                return state;
            }
        }
        throw new InvalidArtistApplicationStateException();
    }

    @JsonValue
    public String getValue() {
        return value;
    }

}
