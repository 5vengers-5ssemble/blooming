package com.fivengers.blooming.global.exception.member;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class MemberTokenNotFoundException extends ApplicationException {

    public MemberTokenNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public MemberTokenNotFoundException() {
        this(ExceptionCode.MEMBER_TOKEN_NOT_FOUND);
    }
}
