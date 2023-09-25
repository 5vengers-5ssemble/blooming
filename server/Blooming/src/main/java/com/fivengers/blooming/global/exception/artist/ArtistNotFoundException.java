package com.fivengers.blooming.global.exception.artist;

import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class ArtistNotFoundException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public ArtistNotFoundException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public ArtistNotFoundException() {
        this(ExceptionCode.ARTIST_NOT_FOUND);
    }

    @Override
    public String toString() {
        return "[EXCEPTION] " + exceptionCode.getErrorCode() + " : " + exceptionCode.getMessage();
    }
}
