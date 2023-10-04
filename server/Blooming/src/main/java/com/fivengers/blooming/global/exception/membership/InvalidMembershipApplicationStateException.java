package com.fivengers.blooming.global.exception.membership;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class InvalidMembershipApplicationStateException extends ApplicationException {

    public InvalidMembershipApplicationStateException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public InvalidMembershipApplicationStateException() {
        this(ExceptionCode.INVALID_MEMBERSHIP_APPLICATION_STATE);
    }
}
