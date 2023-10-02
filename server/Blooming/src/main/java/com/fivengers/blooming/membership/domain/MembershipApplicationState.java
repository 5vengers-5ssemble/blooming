package com.fivengers.blooming.membership.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MembershipApplicationState {
    APPLY("신청"),
    APPROVAL("승인"),
    RETURN("거절"),
    CANCEL("취소");

    private final String value;

    MembershipApplicationState(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
