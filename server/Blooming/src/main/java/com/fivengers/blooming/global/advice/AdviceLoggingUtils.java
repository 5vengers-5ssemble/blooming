package com.fivengers.blooming.global.advice;

import com.fivengers.blooming.global.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdviceLoggingUtils {

    public static void exceptionLog(ApplicationException e) {
        log.info("[EXCEPTION] ({}) {} : {}",
                e.getExceptionCode().getHttpStatus().value(),
                e.getExceptionCode().getErrorCode(),
                e.getExceptionCode().getMessage());
    }
}
