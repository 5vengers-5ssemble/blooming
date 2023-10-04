package com.fivengers.blooming.global.exception.artist;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class ArtistNotFoundException extends ApplicationException {

    public ArtistNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public ArtistNotFoundException() {
        this(ExceptionCode.ARTIST_NOT_FOUND);
    }

}
