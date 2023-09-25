package com.fivengers.blooming.global.exception.live;

import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class SessionNotFoundException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public SessionNotFoundException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public SessionNotFoundException() {
        this(ExceptionCode.SESSION_NOT_FOUND);
    }
}
