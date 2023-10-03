package com.fivengers.blooming.global.exception.stomp;

import com.fivengers.blooming.global.exception.SocketException;
import com.fivengers.blooming.global.exception.SocketExceptionCode;

public class RequiredHeaderMissingFromFrameException extends SocketException {

    public RequiredHeaderMissingFromFrameException(SocketExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public RequiredHeaderMissingFromFrameException() {
        this(SocketExceptionCode.SESSION_ID_NOT_FOUND);
    }
}
