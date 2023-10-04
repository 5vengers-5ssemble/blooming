package com.fivengers.blooming.global.exception.member;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class MemberNotFoundException extends ApplicationException {

    public MemberNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public MemberNotFoundException() {
        this(ExceptionCode.MEMBER_NOT_FOUND);
    }
}
