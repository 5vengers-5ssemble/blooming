package com.fivengers.blooming.global.exception.artist;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class InvalidArtistApplicationStateException extends ApplicationException {

    public InvalidArtistApplicationStateException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public InvalidArtistApplicationStateException() {
        this(ExceptionCode.INVALID_ARTIST_APPLICATION_STATE);
    }
}
