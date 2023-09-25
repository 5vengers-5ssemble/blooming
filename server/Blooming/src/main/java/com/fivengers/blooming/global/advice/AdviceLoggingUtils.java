package com.fivengers.blooming.global.advice;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdviceLoggingUtils {

    public static void exceptionLog(ApplicationException e) {
        log.info("[EXCEPTION] ({}) {} : {}",
                e.getExceptionCode().getHttpStatus().value(),
                e.getExceptionCode().getErrorCode(),
                e.getExceptionCode().getMessage());
    }

    public static void exceptionLog(ExceptionCode exceptionCode, Exception e) {
        log.info("[EXCEPTION] ({}) {} : {}",
                exceptionCode.getHttpStatus().value(),
                exceptionCode.getErrorCode(),
                e.getMessage());
    }
}
