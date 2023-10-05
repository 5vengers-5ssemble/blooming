package com.fivengers.blooming.global.exception.artistvideo;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class ArtistVideoNotFoundException extends ApplicationException {

    public ArtistVideoNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public ArtistVideoNotFoundException() {
        this(ExceptionCode.ARTIST_VIDEO_NOT_FOUND);
    }
}
