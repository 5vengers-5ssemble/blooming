package com.fivengers.blooming.global.advice.member;

import com.fivengers.blooming.global.advice.AdviceLoggingUtils;
import com.fivengers.blooming.global.exception.member.InvalidMemberModifyRequestException;
import com.fivengers.blooming.global.exception.member.MemberNotFoundException;
import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.global.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MemberControllerAdvice {

    @ExceptionHandler(MemberNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<ErrorResponse> memberNotFound(MemberNotFoundException e) {
        AdviceLoggingUtils.exceptionLog(e);
        return ApiResponse.notFound(ErrorResponse.from(e.getExceptionCode()));
    }

    @ExceptionHandler(InvalidMemberModifyRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<ErrorResponse> invalidMemberModifyRequest(
            InvalidMemberModifyRequestException e) {
        AdviceLoggingUtils.exceptionLog(e);
        return ApiResponse.badRequest(ErrorResponse.from(e.getExceptionCode()));
    }
}
