package com.fivengers.blooming.payment.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

public enum ProjectType {
    @Enumerated(EnumType.STRING)
    CONCERT("concert"), ACTIVITY("activity");

    private final String value;

    ProjectType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static ProjectType from(String value) {
        for (ProjectType projectType : ProjectType.values()) {
            if (projectType.getValue().equals(value)) {
                return projectType;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

}
