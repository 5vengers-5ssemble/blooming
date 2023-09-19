package com.fivengers.blooming.global.exception.payment;

import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class InvalidPaymentRequestException extends RuntimeException {
    private final ExceptionCode exceptionCode;

    public InvalidPaymentRequestException(ExceptionCode exceptionCode){
        this.exceptionCode = exceptionCode;
    }

    public InvalidPaymentRequestException(){
        this(ExceptionCode.INVALID_PAYMENT_REQUEST);
    }
}
