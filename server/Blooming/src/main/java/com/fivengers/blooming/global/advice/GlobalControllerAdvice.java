package com.fivengers.blooming.global.advice;

import com.fivengers.blooming.global.exception.ExceptionCode;
import com.fivengers.blooming.global.exception.global.InvalidMethodUsecaseException;
import com.fivengers.blooming.global.exception.global.InvalidSortOrderException;
import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.global.response.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
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

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<ErrorResponse> missingParameterViolation(
            MissingServletRequestParameterException exception) {
        ExceptionCode exceptionCode = ExceptionCode.NULL_PARAMETER;
        AdviceLoggingUtils.exceptionLog(exceptionCode, exception);
        return ApiResponse.badRequest(new ErrorResponse(
                exceptionCode.getErrorCode(),
                exception.getMessage()
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

    @ExceptionHandler(InvalidMethodUsecaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<ErrorResponse> invalidMethodUsecase(
            InvalidMethodUsecaseException exception) {
        AdviceLoggingUtils.exceptionLog(exception.getExceptionCode(), exception);
        return ApiResponse.internalServerError(
                new ErrorResponse(exception.getExceptionCode().getErrorCode(),
                        exception.getMessage()));
    }
}
