package com.fivengers.blooming.global.exception.live;

import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class LiveNotFoundException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public LiveNotFoundException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public LiveNotFoundException() {
        this(ExceptionCode.LIVE_NOT_FOUND);
    }

}
