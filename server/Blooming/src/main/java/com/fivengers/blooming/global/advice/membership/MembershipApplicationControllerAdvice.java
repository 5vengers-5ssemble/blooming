package com.fivengers.blooming.global.advice.membership;

import com.fivengers.blooming.global.advice.AdviceLoggingUtils;
import com.fivengers.blooming.global.exception.membership.InvalidMembershipApplicationModifyRequestException;
import com.fivengers.blooming.global.exception.membership.InvalidMembershipApplicationStateException;
import com.fivengers.blooming.global.exception.membership.MembershipApplicationNotFoundException;
import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.global.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MembershipApplicationControllerAdvice {

    @ExceptionHandler(MembershipApplicationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<ErrorResponse> membershipApplicationNotFound(
            MembershipApplicationNotFoundException e) {
        AdviceLoggingUtils.exceptionLog(e);
        return ApiResponse.notFound(ErrorResponse.from(e.getExceptionCode()));
    }

    @ExceptionHandler(InvalidMembershipApplicationModifyRequestException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<ErrorResponse> invalidMembershipApplicationModifyRequest(
            InvalidMembershipApplicationModifyRequestException e) {
        AdviceLoggingUtils.exceptionLog(e);
        return ApiResponse.badRequest(ErrorResponse.from(e.getExceptionCode()));
    }

    @ExceptionHandler(InvalidMembershipApplicationStateException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<ErrorResponse> invalidMembershipApplicationState(
            InvalidMembershipApplicationStateException e) {
        AdviceLoggingUtils.exceptionLog(e);
        return ApiResponse.badRequest(ErrorResponse.from(e.getExceptionCode()));
    }

}
