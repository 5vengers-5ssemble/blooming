package com.fivengers.blooming.global.exception.emoji;

import com.fivengers.blooming.global.exception.SocketException;
import com.fivengers.blooming.global.exception.SocketExceptionCode;

public class EmojiNotFoundException extends SocketException {

    public EmojiNotFoundException(SocketExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public EmojiNotFoundException() {
        this(SocketExceptionCode.EMOJI_NOT_FOUND);
    }
}
