package com.fivengers.blooming.global.exception.nft;

import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class NftNotFoundException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public NftNotFoundException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public NftNotFoundException() {
        this(ExceptionCode.NFT_NOT_FOUND);
    }
}
