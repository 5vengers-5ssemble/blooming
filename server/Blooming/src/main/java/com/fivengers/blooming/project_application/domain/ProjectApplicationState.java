package com.fivengers.blooming.project_application.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum ProjectApplicationState {

    APPLY("신청"),
    APPROVAL("승인"),
    RETURN("거절"),
    CANCEL("취소");

    private final String value;

    ProjectApplicationState(String value) {
        this.value = value;
    }

    @JsonCreator
    public ProjectApplicationState from(String value) {
        for (ProjectApplicationState state : ProjectApplicationState.values()) {
            if (state.getValue().equals(value)) {
                return state;
            }
        }
        throw new RuntimeException();
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
