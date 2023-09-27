package com.fivengers.blooming.config.security.jwt;

import com.fivengers.blooming.config.security.oauth2.LoginUser;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtSetupService {

    public static final String SET_COOKIE = "Set-Cookie";

    private final JwtProvider jwtProvider;

    @Value("${client.host}")
    private String clientHost;

    @Value("${jwt.access-header}")
    private String accessTokenHeaderTag;

    @Value("${jwt.refresh-header}")
    private String refreshTokenHeaderTag;

    public void addJwtTokensToCookie(HttpServletResponse response, LoginUser loginUser) {
        JwtToken jwtToken = jwtProvider.createJwtToken(loginUser);

        response.addHeader(SET_COOKIE,
                createCookie(accessTokenHeaderTag, jwtToken.getAccessToken()).toString());
        response.addHeader(SET_COOKIE,
                createCookie(refreshTokenHeaderTag, jwtToken.getRefreshToken()).toString());
    }

    private ResponseCookie createCookie(String key, String value) {
        return ResponseCookie.from(key, value)
                .path("/")
                .domain(clientHost)
                .httpOnly(true)
                .sameSite("None")
                .secure(true)
                .build();
    }
}
