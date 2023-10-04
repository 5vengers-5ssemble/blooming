package com.fivengers.blooming.global.exception.live;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;

public class UnauthorizedMemberForClosingLiveException extends ApplicationException {

    public UnauthorizedMemberForClosingLiveException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public UnauthorizedMemberForClosingLiveException() {
        this(ExceptionCode.UNAUTHORIZED_MEMBER_FOR_CLOSING_LIVE);
    }
}
