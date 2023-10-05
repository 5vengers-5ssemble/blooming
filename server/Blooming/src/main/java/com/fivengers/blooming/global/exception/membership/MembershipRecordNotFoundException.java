package com.fivengers.blooming.global.exception.membership;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;

public class MembershipRecordNotFoundException extends ApplicationException {

    public MembershipRecordNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public MembershipRecordNotFoundException() {
        this(ExceptionCode.MEMBERSHIP_RECORD_NOT_FOUND);
    }
}
