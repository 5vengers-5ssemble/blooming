package com.fivengers.blooming.config.security;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

@Component
@RequiredArgsConstructor
public class HttpRequestEndpointChecker {

    private final DispatcherServlet dispatcherServlet;

    public boolean existsEndpoint(HttpServletRequest request) {
        for (HandlerMapping handlerMapping : dispatcherServlet.getHandlerMappings()) {
            try {
                HandlerExecutionChain handler = handlerMapping.getHandler(request);
                if (handler != null) {
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }

        return false;
    }

}
