package com.fivengers.blooming.global.advice.artist;

import com.fivengers.blooming.global.advice.AdviceLoggingUtils;
import com.fivengers.blooming.global.exception.artist.ArtistApplicationNotFoundException;
import com.fivengers.blooming.global.exception.artist.ArtistNotFoundException;
import com.fivengers.blooming.global.exception.artist.InvalidArtistApplicationStateException;
import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.global.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ArtistApplicationControllerAdvice {

    @ExceptionHandler(ArtistApplicationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<ErrorResponse> artistApplicationNotFound(
            ArtistApplicationNotFoundException e) {
        AdviceLoggingUtils.exceptionLog(e);
        return ApiResponse.notFound(ErrorResponse.from(e.getExceptionCode()));
    }

    @ExceptionHandler(InvalidArtistApplicationStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<ErrorResponse> invalidArtistApplicationState(
            InvalidArtistApplicationStateException e) {
        AdviceLoggingUtils.exceptionLog(e);
        return ApiResponse.badRequest(ErrorResponse.from(e.getExceptionCode()));
    }
}
