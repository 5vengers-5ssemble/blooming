package com.fivengers.blooming.global.exception.artistscrap;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class ArtistScrapNotFoundException extends ApplicationException {

    public ArtistScrapNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public ArtistScrapNotFoundException() {
        this(ExceptionCode.ARTIST_SCRAP_NOT_FOUND);
    }
}
