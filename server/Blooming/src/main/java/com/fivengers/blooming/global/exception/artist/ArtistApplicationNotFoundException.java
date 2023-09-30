package com.fivengers.blooming.global.exception.artist;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;

public class ArtistApplicationNotFoundException extends ApplicationException {

    public ArtistApplicationNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public ArtistApplicationNotFoundException() {
        this(ExceptionCode.ARTIST_APPLICATION_NOT_FOUND);
    }
}
