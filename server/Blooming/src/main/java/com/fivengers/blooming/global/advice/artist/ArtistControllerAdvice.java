package com.fivengers.blooming.global.advice.artist;

import com.fivengers.blooming.global.advice.AdviceLoggingUtils;
import com.fivengers.blooming.global.exception.artist.ArtistNotFoundException;
import com.fivengers.blooming.global.exception.artist.InvalidArtistModifyRequestException;
import com.fivengers.blooming.global.exception.artistvideo.ArtistVideoNotFoundException;
import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.global.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ArtistControllerAdvice {

    @ExceptionHandler(ArtistNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<ErrorResponse> artistNotFound(ArtistNotFoundException e) {
        AdviceLoggingUtils.exceptionLog(e);
        return ApiResponse.notFound(ErrorResponse.from(e.getExceptionCode()));
    }

    @ExceptionHandler(InvalidArtistModifyRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<ErrorResponse> invalidArtistModifyRequest(
            InvalidArtistModifyRequestException e) {
        AdviceLoggingUtils.exceptionLog(e);
        return ApiResponse.badRequest(ErrorResponse.from(e.getExceptionCode()));
    }

    @ExceptionHandler(ArtistVideoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<ErrorResponse> artistVideoNotFound(ArtistVideoNotFoundException e) {
        AdviceLoggingUtils.exceptionLog(e);
        return ApiResponse.notFound(ErrorResponse.from(e.getExceptionCode()));
    }
}
