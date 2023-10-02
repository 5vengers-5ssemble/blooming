package com.fivengers.blooming.global.exception.membership;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;

public class MembershipNotFoundException extends ApplicationException {

    public MembershipNotFoundException(
            ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public MembershipNotFoundException() {
        this(ExceptionCode.MEMBERSHIP_NOT_FOUND);
    }
}
