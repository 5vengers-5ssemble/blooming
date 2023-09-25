package com.fivengers.blooming.global.response;

import com.fivengers.blooming.global.exception.ExceptionCode;

public record ErrorResponse(String errorCode,
                            String message) {

    public static ErrorResponse from(ExceptionCode exceptionCode) {
        return new ErrorResponse(exceptionCode.getErrorCode(), exceptionCode.getMessage());
    }
}
