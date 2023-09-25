package com.fivengers.blooming.global.exception.live;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class LiveNotFoundException extends ApplicationException {

    public LiveNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public LiveNotFoundException() {
        this(ExceptionCode.LIVE_NOT_FOUND);
    }

}
