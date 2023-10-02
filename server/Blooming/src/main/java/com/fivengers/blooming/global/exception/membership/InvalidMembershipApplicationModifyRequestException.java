package com.fivengers.blooming.global.exception.membership;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;

public class InvalidMembershipApplicationModifyRequestException extends
        ApplicationException {

    public InvalidMembershipApplicationModifyRequestException(
            ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public InvalidMembershipApplicationModifyRequestException() {
        this(ExceptionCode.INVALID_MEMBERSHIP_APPLICATION_MODIFY_REQUEST);
    }
}
