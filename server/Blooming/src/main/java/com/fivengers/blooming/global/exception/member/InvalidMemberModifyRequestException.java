package com.fivengers.blooming.global.exception.member;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class InvalidMemberModifyRequestException extends ApplicationException {

    public InvalidMemberModifyRequestException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public InvalidMemberModifyRequestException() {
        this(ExceptionCode.INVALID_MEMBER_MODIFY_REQUEST);
    }
}
