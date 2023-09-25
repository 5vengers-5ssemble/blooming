package com.fivengers.blooming.global.exception.project;

import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class InvestmentOverviewNotFoundException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public InvestmentOverviewNotFoundException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public InvestmentOverviewNotFoundException() {
        this(ExceptionCode.PROJECT_NOT_FOUND);
    }
}
