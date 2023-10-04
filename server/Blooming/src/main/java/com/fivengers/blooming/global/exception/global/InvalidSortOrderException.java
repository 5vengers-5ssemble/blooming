package com.fivengers.blooming.global.exception.global;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class InvalidSortOrderException extends ApplicationException {

    public InvalidSortOrderException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public InvalidSortOrderException() {
        this(ExceptionCode.INVALID_ORDER);
    }

}
