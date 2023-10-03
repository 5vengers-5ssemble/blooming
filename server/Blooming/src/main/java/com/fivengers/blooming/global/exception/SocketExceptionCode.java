package com.fivengers.blooming.global.exception;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SocketExceptionCode {
    COMMAND_NOT_FOUND("ERR_SOCKET_REQUEST_001", "Frame에 Command가 없습니다."),
    SESSION_ID_NOT_FOUND("ERR_SOCKET_REQUEST_002", "Frame Header에 sessionId가 없습니다."),

    JWT_EXPIRED("ERR_SOCKET_JWT_001", "JWT 기한이 만료되었습니다."),
    JWT_MALFORMED("ERR_SOCKET_JWT_002", "JWT가 손상되었습니다."),
    JWT_UNSUPPORTED("ERR_SOCKET_JWT_003", "지원되지 않는 JWT 입니다."),
    JWT_INVALID_SIGNATURE("ERR_SOCKET_JWT_004", "signature가 유효하지 않습니다."),
    JWT_NOT_FOUND("ERR_SOCKET_JWT_005", "JWT를 찾을 수 없습니다."),
    JWT_PARSING_FAILED("ERR_SOCKET_JWT_006", "잘못된 JWT 토큰입니다.");

    private final String errorCode;
    private final String message;

    public String stringify() {
        Gson gson = new Gson();
        Map<String, String> socketException = new HashMap<>();
        socketException.put("errorCode", errorCode);
        socketException.put("message", message);
        return gson.toJson(socketException);
    }
}
