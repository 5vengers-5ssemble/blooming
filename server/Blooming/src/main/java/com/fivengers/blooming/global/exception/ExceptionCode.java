package com.fivengers.blooming.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    INVALID_MEMBER_MODIFY_REQUEST(HttpStatus.BAD_REQUEST, "ERR_MEMBER_001", "유효하지 않은 회원 수정 요청입니다."),

    NFT_SALE_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR_NFT_SALE_001", "NFT 판매 집계를 찾을 수 없습니다."),

    NFT_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR_NFT_001", "NFT를 찾을 수 없습니다"),

    ARTIST_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR_ARTIST_001", "아티스트를 찾을 수 없습니다."),
    INVALID_ARTIST_MODIFY_REQUEST(HttpStatus.BAD_REQUEST, "ERR_ARTIST_002", "유효하지 않은 아티스트 수정 요청입니다."),
    ARTIST_APPLICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR_ARTIST_003", "아티스트 신청 기록을 찾을 수 없습니다."),
    INVALID_ARTIST_APPLICATION_STATE(HttpStatus.BAD_REQUEST, "ERR_ARTIST_004", "유효하지 않은 아티스트 신청 상태입니다."),

    ARTIST_VIDEO_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR_ARTIST_VIDEO_001", "아티스트 비디오를 찾을 수 없습니다."),

    MEMBERSHIP_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR_MEMBERSHIP_001", "멤버십을 찾을 수 없습니다."),
    INVALID_MEMBERSHIP_MODIFY_REQUEST(HttpStatus.BAD_REQUEST, "ERR_MEMBERSHIP_002",
            "유효하지 않은 멤버십 수정 요청입니다."),
    MEMBERSHIP_APPLICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR_MEMBERSHIP_003",
            "멤버십 신청 기록을 찾을 수 없습니다."),
    INVALID_MEMBERSHIP_APPLICATION_MODIFY_REQUEST(HttpStatus.BAD_REQUEST, "ERR_MEMBERSHIP_004",
            "유효하지 않은 멤버십 신청 수정 요청입니다."),
    INVALID_MEMBERSHIP_APPLICATION_STATE(HttpStatus.BAD_REQUEST, "ERR_MEMBERSHIP_005", "유효하지 않은 멤버십 신청 상태입니다."),

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR_MEMBER_001", "멤버를 찾을 수 없습니다."),

    ARTIST_SCRAP_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR_ARTIST_SCRAP_001", "아티스트 관심을 찾을 수 없습니다."),

    ARTIST_SCRAP_RECORD_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR_ARTIST_SCRAP_RECORD_001",
            "아티스트 관심 기록을 찾을 수 없습니다."),

    PROJECT_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR_PROJECT_001", "펀딩 프로젝트를 찾을 수 없습니다."),

    INVESTMENT_OVERVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR_INVESTMENT_OVERVIEW_001",
            "투자 개요를 찾을 수 없습니다."),
    INVALID_PAYMENT_REQUEST(HttpStatus.BAD_REQUEST,
            "ERR_PAYMENT_001", "잘못된 결제 요청입니다."),

    INVALID_ORDER(HttpStatus.NOT_FOUND, "ERR_GLOBAL_001", "유효한 정렬이 아닙니다."),
    CONSTRAINT_VIOLATION(HttpStatus.BAD_REQUEST, "ERR_GLOBAL_002", "유효한 파라미터 값이 아닙니다."),
    UNKNOWN_SERVER_LOGIC(HttpStatus.INTERNAL_SERVER_ERROR, "ERR_GLOBAL_003",
            "알 수 없는 로직 에러가 발생하였습니다."),
    NULL_PARAMETER(HttpStatus.BAD_REQUEST, "ERR_GLOBAL_004", "null값을 가진 파라미터가 전달되었습니다."),
    INVALID_METHOD_USECASE(HttpStatus.INTERNAL_SERVER_ERROR, "ERR_GLOBAL_005", "서버에서 유효하지 않은 메서드 호출이 발생했습니다."),
    UNREGISTERED_EXCEPTION(HttpStatus.BAD_REQUEST, "ERR_GLOBAL_999", "등록되지 않은 오류입니다."),

    JWT_EXPIRED(HttpStatus.UNAUTHORIZED, "ERR_JWT_001", "JWT 기한이 만료되었습니다."),
    JWT_MALFORMED(HttpStatus.UNAUTHORIZED, "ERR_JWT_002", "JWT가 손상되었습니다."),
    JWT_UNSUPPORTED(HttpStatus.UNAUTHORIZED, "ERR_JWT_003", "지원되지 않는 JWT 입니다."),
    JWT_INVALID_SIGNATURE(HttpStatus.UNAUTHORIZED, "ERR_JWT_004", "signature가 유효하지 않습니다."),
    JWT_NOT_FOUND(HttpStatus.UNAUTHORIZED, "ERR_JWT_005", "JWT를 찾을 수 없습니다."),

    SESSION_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR_LIVE_001", "존재하지 않는 세션입니다."),
    INVALID_SESSION_ID(HttpStatus.BAD_REQUEST, "ERR_LIVE_002", "유효하지 않은 Session Id 입니다,"),
    LIVE_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR_LIVE_003", "해당 라이브는 존재하지 않습니다."),
    OPENVIDU_WEBHOOK_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR_LIVE_004", "올바르지 않은 Openvidu WebHook 입니다."),
    UNAUTHORIZED_MEMBER_FOR_CLOSING_LIVE(HttpStatus.BAD_REQUEST, "ERR_LIVE_005", "해당 멤버는 해당 라이브를 종료할 수 없습니다."),
    MOTION_MODEL_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR_LIVE_006", "사용가능한 모션 모델이 없습니다");


    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String message;
}
