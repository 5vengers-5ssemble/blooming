package com.fivengers.blooming.global.exception.project;

import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class ProjectNotFoundException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public ProjectNotFoundException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public ProjectNotFoundException() {
        this(ExceptionCode.PROJECT_NOT_FOUND);
    }
}
