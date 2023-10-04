package com.fivengers.blooming.global.exception.jwt;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;

public class JwtNotFoundException extends ApplicationException {

    public JwtNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public JwtNotFoundException() {
        this(ExceptionCode.JWT_NOT_FOUND);
    }
}
