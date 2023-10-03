package com.fivengers.blooming.global.exception.live;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;

public class MotionModelNotFoundException extends ApplicationException {

    public MotionModelNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public MotionModelNotFoundException() {
        this(ExceptionCode.MOTION_MODEL_NOT_FOUND);
    }
}
