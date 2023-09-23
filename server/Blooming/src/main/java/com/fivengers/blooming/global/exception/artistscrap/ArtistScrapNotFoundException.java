package com.fivengers.blooming.global.exception.artistscrap;

import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class ArtistScrapNotFoundException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public ArtistScrapNotFoundException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public ArtistScrapNotFoundException() {
        this(ExceptionCode.ARTIST_SCRAP_NOT_FOUND);
    }
}
