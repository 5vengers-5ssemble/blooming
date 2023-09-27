package com.fivengers.blooming.config.security.jwt;

import com.fivengers.blooming.config.security.oauth2.LoginUser;
import com.fivengers.blooming.config.security.oauth2.mapper.LoginUserMapper;
import com.fivengers.blooming.member.application.port.MemberService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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
                memberService.findById(Long.parseLong(getTokenClaims(token).get("id", String.class))));
    }

    private Claims getTokenClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
