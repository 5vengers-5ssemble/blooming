package com.fivengers.blooming.global.exception.stomp;

import com.fivengers.blooming.global.exception.SocketException;
import com.fivengers.blooming.global.exception.SocketExceptionCode;

public class SessionIdNotFoundFromFrameException extends SocketException {

    public SessionIdNotFoundFromFrameException(SocketExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public SessionIdNotFoundFromFrameException() {
        this(SocketExceptionCode.SESSION_ID_NOT_FOUND);
    }
}
