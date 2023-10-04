package com.fivengers.blooming.global.exception.membership;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;

public class InvalidMembershipModifyRequestException extends ApplicationException {

    public InvalidMembershipModifyRequestException(
            ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public InvalidMembershipModifyRequestException() {
        this(ExceptionCode.INVALID_MEMBERSHIP_MODIFY_REQUEST);
    }
}
