package com.fivengers.blooming.global.exception.global;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;

public class InvalidMethodUsecaseException extends ApplicationException {

    public InvalidMethodUsecaseException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public InvalidMethodUsecaseException() {
        this(ExceptionCode.INVALID_METHOD_USECASE);
    }

}
