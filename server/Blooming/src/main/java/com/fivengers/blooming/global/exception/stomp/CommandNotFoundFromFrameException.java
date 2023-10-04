package com.fivengers.blooming.global.exception.stomp;

import com.fivengers.blooming.global.exception.SocketException;
import com.fivengers.blooming.global.exception.SocketExceptionCode;

public class CommandNotFoundFromFrameException extends SocketException {

    public CommandNotFoundFromFrameException(SocketExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public CommandNotFoundFromFrameException() {
        this(SocketExceptionCode.COMMAND_NOT_FOUND);
    }
}
