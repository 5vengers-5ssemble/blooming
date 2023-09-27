package com.fivengers.blooming.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fivengers.blooming.config.security.jwt.JwtProvider;
import com.fivengers.blooming.config.security.oauth2.OAuth2Request;
import com.fivengers.blooming.config.security.oauth2.mapper.LoginUserMapper;
import com.fivengers.blooming.member.application.port.MemberService;
import com.fivengers.blooming.member.domain.AuthProvider;
import com.fivengers.blooming.member.domain.Member;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class RestEndToEndTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private MemberService memberService;
    @Autowired
    private LoginUserMapper loginUserMapper;

    protected Member member;

    public static final String AUTHORIZATION = "Authorization";

    protected String toJson(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }

    @Transactional
    protected String getAccessToken() {
        member = memberService.saveIfNotExists(
                new OAuth2Request(UUID.randomUUID().toString(),
                        AuthProvider.KAKAO,
                        "mock",
                        "mock"));
        return jwtProvider.createJwtToken(loginUserMapper.toLoginUser(
                        member))
                .getAccessToken();
    }
}
