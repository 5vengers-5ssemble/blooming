package com.fivengers.blooming.config.security.jwt;

import com.fivengers.blooming.global.exception.ApplicationException;
import com.fivengers.blooming.global.exception.ExceptionCode;
import com.fivengers.blooming.global.exception.ExceptionResponseUtils;
import com.fivengers.blooming.global.exception.jwt.JwtNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        try {
            getTokensFromHeader(request).ifPresentOrElse(this::setTokenInSecurityContext,
                    () -> {
                        throw new JwtNotFoundException();
                    });

            doFilter(request, response, filterChain);
        } catch (ExpiredJwtException e) {
            ExceptionResponseUtils.responseHttpException(response, ExceptionCode.JWT_EXPIRED,
                    ExceptionCode.JWT_EXPIRED.getMessage());
        } catch (MalformedJwtException e) {
            ExceptionResponseUtils.responseHttpException(response, ExceptionCode.JWT_MALFORMED,
                    ExceptionCode.JWT_MALFORMED.getMessage());
        } catch (UnsupportedJwtException e) {
            ExceptionResponseUtils.responseHttpException(response, ExceptionCode.JWT_UNSUPPORTED,
                    ExceptionCode.JWT_UNSUPPORTED.getMessage());
        } catch (SignatureException e) {
            ExceptionResponseUtils.responseHttpException(response,
                    ExceptionCode.JWT_INVALID_SIGNATURE,
                    ExceptionCode.JWT_INVALID_SIGNATURE.getMessage());
        } catch (ApplicationException e) {
            ExceptionResponseUtils.responseHttpException(response, e.getExceptionCode(),
                    e.getExceptionCode().getMessage());
        } catch (Exception e) {
            ExceptionResponseUtils.responseHttpException(response,
                    ExceptionCode.UNREGISTERED_EXCEPTION,
                    e.getMessage());
        }
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
