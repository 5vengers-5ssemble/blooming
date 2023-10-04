package com.fivengers.blooming.global.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.global.response.ErrorResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import org.springframework.http.MediaType;

public class ExceptionResponseUtils {

    static class Mapper {

        private static final ObjectMapper INSTANCE = new ObjectMapper();

        private Mapper() { }

        public static ObjectMapper getInstance() {
            return INSTANCE;
        }
    }

    public static void responseHttpException(HttpServletResponse response, ExceptionCode exceptionCode,
            String message) throws IOException {
        response.setStatus(exceptionCode.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        PrintWriter writer = response.getWriter();
        writer.write(Mapper.getInstance().writeValueAsString(ApiResponse
                .status(exceptionCode.getHttpStatus())
                .body(new ErrorResponse(exceptionCode.getErrorCode(), message))));
        writer.flush();
    }

}
