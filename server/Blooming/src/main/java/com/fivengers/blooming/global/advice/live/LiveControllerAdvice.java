package com.fivengers.blooming.global.advice.live;

import com.fivengers.blooming.global.advice.AdviceLoggingUtils;
import com.fivengers.blooming.global.exception.ExceptionCode;
import com.fivengers.blooming.global.exception.live.InvalidSessionIdException;
import com.fivengers.blooming.global.exception.live.LiveNotFoundException;
import com.fivengers.blooming.global.exception.live.OpenviduWebHookNotFoundException;
import com.fivengers.blooming.global.exception.live.SessionNotFoundException;
import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.global.response.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class LiveControllerAdvice {

    @ExceptionHandler(SessionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<ErrorResponse> sessionNotFound(SessionNotFoundException exception) {
        log.info("[EXCEPTION] {} : {}",
                exception.getExceptionCode().getErrorCode(),
                exception.getExceptionCode().getMessage());

        return ApiResponse.notFound(ErrorResponse.from(exception.getExceptionCode()));
    }

    @ExceptionHandler(InvalidSessionIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<ErrorResponse> invalidSessionId(InvalidSessionIdException exception) {
        log.info("[EXCEPTION] {} : {}",
                exception.getExceptionCode().getErrorCode(),
                exception.getExceptionCode().getMessage());

        return ApiResponse.badRequest(ErrorResponse.from(exception.getExceptionCode()));
    }

    @ExceptionHandler(LiveNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<ErrorResponse> liveNotFound(LiveNotFoundException exception) {
        log.info("[EXCEPTION] {} : {}",
                exception.getExceptionCode().getErrorCode(),
                exception.getExceptionCode().getMessage());

        return ApiResponse.notFound(ErrorResponse.from(exception.getExceptionCode()));
    }

    @ExceptionHandler(OpenviduWebHookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<ErrorResponse> constraintViolation(OpenviduWebHookNotFoundException exception) {
        AdviceLoggingUtils.exceptionLog(ExceptionCode.OPENVIDU_WEBHOOK_NOT_FOUND, exception);
        return ApiResponse.notFound(new ErrorResponse(
                ExceptionCode.OPENVIDU_WEBHOOK_NOT_FOUND.getErrorCode(),
                exception.getMessage()
        ));
    }
}
