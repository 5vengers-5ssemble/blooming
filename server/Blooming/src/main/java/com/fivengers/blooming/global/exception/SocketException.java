package com.fivengers.blooming.global.exception;

import lombok.Getter;

@Getter
public class SocketException extends RuntimeException{

    private final SocketExceptionCode exceptionCode;

    public SocketException(SocketExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public SocketException(SocketExceptionCode exceptionCode, String message) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

}
