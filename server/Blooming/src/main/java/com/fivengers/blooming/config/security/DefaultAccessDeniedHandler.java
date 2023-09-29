package com.fivengers.blooming.config.security;

import com.fivengers.blooming.global.exception.ExceptionCode;
import com.fivengers.blooming.global.exception.ExceptionResponseUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

@Slf4j
@RequiredArgsConstructor
public class DefaultAccessDeniedHandler extends AccessDeniedHandlerImpl {

    public static final String NOT_FOUND_SERVLET_MSG = "해당 접근은 유효하지 않습니다.";

    private final HttpRequestEndpointChecker endpointChecker;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {

        if (!endpointChecker.existsEndpoint(request)) {
            log.info("[AccessDeniedHandler] {} {} 유효하지 않은 접근입니다.", request.getMethod(),
                    request.getRequestURI());
            ExceptionResponseUtils.responseHttpException(response,
                    ExceptionCode.UNREGISTERED_EXCEPTION, NOT_FOUND_SERVLET_MSG);
            return;
        }

        super.handle(request, response, accessDeniedException);
    }
}
