package com.fivengers.blooming.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    ;

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String message;
}
