package com.fivengers.blooming.global.exception.artistscrap;

import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class ArtistScrapRecordNotFoundException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public ArtistScrapRecordNotFoundException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public ArtistScrapRecordNotFoundException() {
        this(ExceptionCode.ARTIST_SCRAP_RECORD_NOT_FOUND);
    }
}
