package com.fivengers.blooming.global.exception.payment;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class InvalidPaymentRequestException extends ApplicationException {

    public InvalidPaymentRequestException(ExceptionCode exceptionCode){
        super(exceptionCode);
    }

    public InvalidPaymentRequestException(){
        this(ExceptionCode.INVALID_PAYMENT_REQUEST);
    }
}
