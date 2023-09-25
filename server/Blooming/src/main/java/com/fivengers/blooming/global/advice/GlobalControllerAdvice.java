package com.fivengers.blooming.global.advice;

import com.fivengers.blooming.global.exception.ExceptionCode;
import com.fivengers.blooming.global.exception.global.InvalidSortOrderException;
import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.global.response.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(InvalidSortOrderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<ErrorResponse> invalidSortOrder(InvalidSortOrderException exception) {
        AdviceLoggingUtils.exceptionLog(exception);
        return ApiResponse.badRequest(ErrorResponse.from(exception.getExceptionCode()));
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<ErrorResponse> constraintViolation(ConstraintViolationException exception) {
        AdviceLoggingUtils.exceptionLog(ExceptionCode.CONSTRAINT_VIOLATION, exception);
        return ApiResponse.badRequest(new ErrorResponse(
                ExceptionCode.CONSTRAINT_VIOLATION.getErrorCode(),
                exception.getMessage()
        ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<ErrorResponse> nullParameterViolation(MethodArgumentNotValidException exception) {
        ExceptionCode exceptionCode = ExceptionCode.UNREGISTERED_EXCEPTION;
        String errorMessage = exception.getMessage();
        if (exception.getMessage().contains("널")) {
            exceptionCode = ExceptionCode.NULL_PARAMETER;
            errorMessage = exceptionCode.getMessage();
        }
        AdviceLoggingUtils.exceptionLog(exceptionCode, exception);
        return ApiResponse.badRequest(new ErrorResponse(
                exceptionCode.getErrorCode(),
                errorMessage
        ));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<ErrorResponse> invalidJson(HttpMessageNotReadableException exception) {
        ExceptionCode exceptionCode = ExceptionCode.UNREGISTERED_EXCEPTION;
        AdviceLoggingUtils.exceptionLog(exceptionCode, exception);
        return ApiResponse.badRequest(new ErrorResponse(
                exceptionCode.getErrorCode(),
                exception.getMessage()
        ));
    }

}
