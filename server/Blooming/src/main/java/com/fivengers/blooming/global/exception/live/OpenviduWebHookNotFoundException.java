package com.fivengers.blooming.global.exception.live;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;

public class OpenviduWebHookNotFoundException extends ApplicationException {

    public OpenviduWebHookNotFoundException(
            ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public OpenviduWebHookNotFoundException() {
        this(ExceptionCode.OPENVIDU_WEBHOOK_NOT_FOUND);
    }
}
