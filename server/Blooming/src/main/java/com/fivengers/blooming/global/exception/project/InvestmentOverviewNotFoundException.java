package com.fivengers.blooming.global.exception.project;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class InvestmentOverviewNotFoundException extends ApplicationException {

    public InvestmentOverviewNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public InvestmentOverviewNotFoundException() {
        this(ExceptionCode.PROJECT_NOT_FOUND);
    }
}
