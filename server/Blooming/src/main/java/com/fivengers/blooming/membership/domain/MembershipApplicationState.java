package com.fivengers.blooming.membership.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fivengers.blooming.global.exception.membership.InvalidMembershipApplicationStateException;

public enum MembershipApplicationState {
    APPLY("신청"),
    APPROVAL("승인"),
    RETURN("거절"),
    CANCEL("취소");

    private final String value;

    MembershipApplicationState(String value) {
        this.value = value;
    }

    @JsonCreator
    public MembershipApplicationState from(String value) {
        for (MembershipApplicationState state : MembershipApplicationState.values()) {
            if (state.getValue().equals(value)) {
                return state;
            }
        }
        throw new InvalidMembershipApplicationStateException();
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
