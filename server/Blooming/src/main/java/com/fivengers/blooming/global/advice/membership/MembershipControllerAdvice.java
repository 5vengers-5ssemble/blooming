package com.fivengers.blooming.global.advice.membership;

import com.fivengers.blooming.global.advice.AdviceLoggingUtils;
import com.fivengers.blooming.global.exception.membership.InvalidMembershipModifyRequestException;
import com.fivengers.blooming.global.exception.membership.MembershipNotFoundException;
import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.global.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MembershipControllerAdvice {

    @ExceptionHandler(MembershipNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<ErrorResponse> membershipNotFound(MembershipNotFoundException e) {
        AdviceLoggingUtils.exceptionLog(e);
        return ApiResponse.notFound(ErrorResponse.from(e.getExceptionCode()));
    }

    @ExceptionHandler(InvalidMembershipModifyRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<ErrorResponse> invalidMembershipModifyRequest(
            InvalidMembershipModifyRequestException e) {
        AdviceLoggingUtils.exceptionLog(e);
        return ApiResponse.badRequest(ErrorResponse.from(e.getExceptionCode()));
    }
}
