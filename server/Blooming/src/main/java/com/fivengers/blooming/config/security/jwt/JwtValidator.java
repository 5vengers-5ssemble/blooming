package com.fivengers.blooming.config.security.jwt;

import com.fivengers.blooming.config.security.oauth2.LoginUser;
import com.fivengers.blooming.config.security.oauth2.mapper.LoginUserMapper;
import com.fivengers.blooming.global.exception.SocketExceptionCode;
import com.fivengers.blooming.global.exception.stomp.SocketAuthorizationException;
import com.fivengers.blooming.member.application.port.MemberService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import java.security.Key;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtValidator {

    private final Key key;
    private final MemberService memberService;
    private final LoginUserMapper loginUserMapper;

    public Authentication getAuthentication(String accessToken) {
        LoginUser loginUser = findLoginUserByToken(accessToken);
        return new UsernamePasswordAuthenticationToken(loginUser, "", loginUser.getAuthorities());
    }

    public LoginUser findLoginUserByToken(String token) {
        return loginUserMapper.toLoginUser(
                memberService.searchById(
                        Long.parseLong(getTokenClaims(token).get("id", String.class))));
    }

    public Claims getTokenClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public void validateAccessToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
        } catch (MalformedJwtException e) {
            throw new SocketAuthorizationException(SocketExceptionCode.JWT_MALFORMED);
        } catch (ExpiredJwtException e) {
            throw new SocketAuthorizationException(SocketExceptionCode.JWT_EXPIRED);
        } catch (UnsupportedJwtException e) {
            throw new SocketAuthorizationException(SocketExceptionCode.JWT_UNSUPPORTED);
        } catch (SignatureException e) {
            throw new SocketAuthorizationException(SocketExceptionCode.JWT_INVALID_SIGNATURE);
        } catch (IllegalArgumentException e) {
            throw new SocketAuthorizationException(SocketExceptionCode.JWT_PARSING_FAILED, e.getMessage());
        }
    }
}
