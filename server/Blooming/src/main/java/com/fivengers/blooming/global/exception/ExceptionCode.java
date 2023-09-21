package com.fivengers.blooming.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    NFT_SALE_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR_NFT_SALE_001", "NFT 판매 집계를 찾을 수 없습니다."),

    NFT_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR_NFT_001", "NFT를 찾을 수 없습니다"),

    ARTIST_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR_ARTIST_001", "아티스트를 찾을 수 없습니다."),

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR_MEMBER_001", "멤버를 찾을 수 없습니다."),

    PROJECT_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR_PROJECT_001", "펀딩 프로젝트를 찾을 수 없습니다."),

    INVESTMENT_OVERVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR_INVESTMENT_OVERVIEW_001", "투자 개요를 찾을 수 없습니다."),
    INVALID_PAYMENT_REQUEST(HttpStatus.BAD_REQUEST,
            "ERR_PAYMENT_001", "잘못된 결제 요청입니다.");

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String message;
}
