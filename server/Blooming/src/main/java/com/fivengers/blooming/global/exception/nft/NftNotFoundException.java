package com.fivengers.blooming.global.exception.nft;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class NftNotFoundException extends ApplicationException {

    public NftNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public NftNotFoundException() {
        this(ExceptionCode.NFT_NOT_FOUND);
    }
}
