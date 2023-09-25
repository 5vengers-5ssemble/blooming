package com.fivengers.blooming.global.exception.live;

import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class InvalidSessionIdException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public InvalidSessionIdException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public InvalidSessionIdException() {
        this(ExceptionCode.INVALID_SESSION_ID);
    }

}
