package com.fivengers.blooming.global.exception.live;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class InvalidSessionIdException extends ApplicationException {

    public InvalidSessionIdException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public InvalidSessionIdException() {
        this(ExceptionCode.INVALID_SESSION_ID);
    }

}
