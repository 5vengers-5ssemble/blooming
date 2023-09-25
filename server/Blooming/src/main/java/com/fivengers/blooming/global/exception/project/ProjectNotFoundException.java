package com.fivengers.blooming.global.exception.project;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class ProjectNotFoundException extends ApplicationException {

    public ProjectNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public ProjectNotFoundException() {
        super(ExceptionCode.PROJECT_NOT_FOUND);
    }
}
