package com.fivengers.blooming.global.exception.live;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class SessionNotFoundException extends ApplicationException {

    public SessionNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public SessionNotFoundException() {
        this(ExceptionCode.SESSION_NOT_FOUND);
    }
}
