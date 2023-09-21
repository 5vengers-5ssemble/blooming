package com.fivengers.blooming.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    NFT_SALE_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR_NFT_SALE_001", "NFT 판매 집계를 찾을 수 없습니다."),

    NFT_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR_NFT_001", "NFT를 찾을 수 없습니다"),

    ARTIST_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR_ARTIST_001", "아티스트를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String message;
}
