package com.fivengers.blooming.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    INVALID_PAYMENT_REQUEST(HttpStatus.BAD_REQUEST,
            "ERR_PAYMENT_001", "잘못된 결제 요청입니다.");

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String message;
}
