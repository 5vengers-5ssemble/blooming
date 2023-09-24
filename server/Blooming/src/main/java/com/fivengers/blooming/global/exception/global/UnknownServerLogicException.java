package com.fivengers.blooming.global.exception.global;

import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

// 서버 내부의 알수 없는 로직 에러
@Getter
public class UnknownServerLogicException extends RuntimeException {

    private ExceptionCode exceptionCode;

    public UnknownServerLogicException(ExceptionCode exceptionCode) {
//        super("알 수 없는 로직 에러가 발생하였습니다.");
        this.exceptionCode = exceptionCode;
    }

    public UnknownServerLogicException() {
        super(ExceptionCode.UNKNOWN_SERVER_LOGIC.getMessage());
    }

}
