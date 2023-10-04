package com.fivengers.blooming.global.exception.nftsale;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class NftSaleNotFoundException extends ApplicationException {

    public NftSaleNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public NftSaleNotFoundException() {
        this(ExceptionCode.NFT_SALE_NOT_FOUND);
    }
}
