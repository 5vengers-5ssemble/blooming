package com.fivengers.blooming.global.exception.nftsale;

import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class NftSaleNotFoundException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public NftSaleNotFoundException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public NftSaleNotFoundException() {
        this(ExceptionCode.NFT_SALE_NOT_FOUND);
    }
}
