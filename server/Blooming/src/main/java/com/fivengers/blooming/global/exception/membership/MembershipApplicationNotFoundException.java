package com.fivengers.blooming.global.exception.membership;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;

public class MembershipApplicationNotFoundException extends ApplicationException {

    public MembershipApplicationNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public MembershipApplicationNotFoundException() {
        this(ExceptionCode.MEMBERSHIP_APPLICATION_NOT_FOUND);
    }
}
