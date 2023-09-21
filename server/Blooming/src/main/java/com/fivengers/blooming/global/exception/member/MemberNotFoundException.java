package com.fivengers.blooming.global.exception.member;

import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class MemberNotFoundException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public MemberNotFoundException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public MemberNotFoundException() {
        this(ExceptionCode.MEMBER_NOT_FOUND);
    }
}
