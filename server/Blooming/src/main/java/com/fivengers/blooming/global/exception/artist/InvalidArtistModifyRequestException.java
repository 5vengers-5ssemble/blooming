package com.fivengers.blooming.global.exception.artist;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class InvalidArtistModifyRequestException extends ApplicationException {

    public InvalidArtistModifyRequestException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public InvalidArtistModifyRequestException() {
        this(ExceptionCode.INVALID_ARTIST_MODIFY_REQUEST);
    }
}
