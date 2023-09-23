package com.fivengers.blooming.global.exception.global;

import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class InvalidSortOrderException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public InvalidSortOrderException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public InvalidSortOrderException() {
        this(ExceptionCode.INVALID_ORDER);
    }

}