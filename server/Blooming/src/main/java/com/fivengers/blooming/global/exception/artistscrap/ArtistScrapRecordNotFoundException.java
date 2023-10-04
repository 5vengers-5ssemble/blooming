package com.fivengers.blooming.global.exception.artistscrap;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class ArtistScrapRecordNotFoundException extends ApplicationException {

    public ArtistScrapRecordNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public ArtistScrapRecordNotFoundException() {
        this(ExceptionCode.ARTIST_SCRAP_RECORD_NOT_FOUND);
    }
}
