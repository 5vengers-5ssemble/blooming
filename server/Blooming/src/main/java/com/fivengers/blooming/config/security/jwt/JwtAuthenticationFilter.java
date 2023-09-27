package com.fivengers.blooming.config.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_TAG = "Authorization";
    private final JwtValidator jwtValidator;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        getTokensFromHeader(request).ifPresent(this::setTokenInSecurityContext);

        doFilter(request, response, filterChain);
    }

    private void setTokenInSecurityContext(String token) {
        loggingToken(token);
        SecurityContextHolder.getContext().setAuthentication(jwtValidator.getAuthentication(token));
    }

    private static void loggingToken(String t) {
        log.info("[JwtAuthenticationFilter] AccessToken: {}", t);
    }

    private Optional<String> getTokensFromHeader(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(AUTHORIZATION_TAG));
    }
}
