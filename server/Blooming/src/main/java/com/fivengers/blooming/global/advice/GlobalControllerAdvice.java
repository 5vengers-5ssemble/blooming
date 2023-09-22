package com.fivengers.blooming.global.advice;

import com.fivengers.blooming.global.exception.global.InvalidSortOrderException;
import com.fivengers.blooming.global.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(InvalidSortOrderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<String> invalidSortOrder(InvalidSortOrderException exception) {
        log.info("[EXCEPTION] {} : {}",
                exception.getExceptionCode().getErrorCode(),
                exception.getExceptionCode().getMessage());

        return ApiResponse.badRequest(exception.getExceptionCode().getMessage());
    }
}
