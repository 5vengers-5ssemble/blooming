package com.fivengers.blooming.config.security.jwt;

import com.fivengers.blooming.config.security.oauth2.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private static final Long AUTH_TOKEN_VALIDATION_SECOND = 60L * 1000;
    private static final Long ACCESS_TOKEN_VALIDATION_SECOND = 60L * 60 * 2 * 1000;
    private static final Long REFRESH_TOKEN_VALIDATION_SECOND = 60L * 60 * 24 * 14 * 1000;
    private static final String GRANT_TYPE = "Bearer";

    private final Key key;
    private final JwtValidator jwtValidator;

    public JwtToken createJwtToken(LoginUser loginUser) {
        Claims claims = generateClaims(loginUser);

        String accessToken = generateToken(loginUser, claims, ACCESS_TOKEN_VALIDATION_SECOND);
        String refreshToken = generateToken(loginUser, claims, REFRESH_TOKEN_VALIDATION_SECOND);

        return new JwtToken(accessToken, refreshToken, GRANT_TYPE);
    }

    public JwtToken createJwtTokenByAuthToken(String authToken) {
        return createJwtToken(jwtValidator.findLoginUserByToken(authToken));
    }

    public String createAuthToken(LoginUser loginUser) {
        return generateToken(loginUser, generateClaims(loginUser), AUTH_TOKEN_VALIDATION_SECOND);
    }

    private Claims generateClaims(LoginUser loginUser) {
        Claims claims = Jwts.claims();
        claims.put("id", loginUser.getName());
        return claims;
    }

    private String generateToken(LoginUser loginUser, Claims claims, Long validationSecond) {
        long now = new Date().getTime();
        return Jwts.builder()
                .setSubject(loginUser.getName())
                .setClaims(claims)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(new Date(now + validationSecond))
                .compact();
    }

}
