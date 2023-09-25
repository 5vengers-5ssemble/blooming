package com.fivengers.blooming.global.exception.global;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

// 서버 내부의 알수 없는 로직 에러
@Getter
public class UnknownServerLogicException extends ApplicationException {

    public UnknownServerLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public UnknownServerLogicException() {
        this(ExceptionCode.UNKNOWN_SERVER_LOGIC);
    }

}
