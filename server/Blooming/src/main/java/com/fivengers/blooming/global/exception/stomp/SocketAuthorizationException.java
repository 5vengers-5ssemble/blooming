package com.fivengers.blooming.global.exception.stomp;

import com.fivengers.blooming.global.exception.SocketException;
import com.fivengers.blooming.global.exception.SocketExceptionCode;

public class SocketAuthorizationException extends SocketException {

    public SocketAuthorizationException(SocketExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public SocketAuthorizationException(SocketExceptionCode exceptionCode, String message) {
        super(exceptionCode, message);
    }

}
