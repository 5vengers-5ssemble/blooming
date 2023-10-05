package com.fivengers.blooming.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fivengers.blooming.config.security.jwt.JwtProvider;
import com.fivengers.blooming.config.security.oauth2.OAuth2Request;
import com.fivengers.blooming.config.security.oauth2.mapper.LoginUserMapper;
import com.fivengers.blooming.member.adapter.out.persistence.entity.MemberJpaEntity;
import com.fivengers.blooming.member.adapter.out.persistence.mapper.MemberMapper;
import com.fivengers.blooming.member.application.port.MemberService;
import com.fivengers.blooming.member.domain.AuthProvider;
import com.fivengers.blooming.member.domain.Member;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class RestEndToEndTest {

    @Autowired private ObjectMapper objectMapper;
    @Autowired private JwtProvider jwtProvider;
    @Autowired private MemberService memberService;
    @Autowired private MemberMapper memberMapper;
    @Autowired private LoginUserMapper loginUserMapper;
    @Autowired protected DatabaseCleaner databaseCleaner;

    protected Member authMember;

    public static final String AUTHORIZATION = "Authorization";

    protected String toJson(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }

    protected String getAccessToken() {
        authMember = memberService.saveIfNotExists(
                new OAuth2Request(UUID.randomUUID().toString(),
                        AuthProvider.KAKAO,
                        "mock",
                        "mock"));
        return jwtProvider.createJwtToken(loginUserMapper.toLoginUser(
                        authMember))
                .getAccessToken();
    }

    protected String getAccessToken(Member member) {
        return jwtProvider.createJwtToken(loginUserMapper.toLoginUser(member))
                .getAccessToken();
    }

    protected String getAccessToken(MemberJpaEntity memberJpaEntity) {
        return jwtProvider.createJwtToken(
                        loginUserMapper.toLoginUser(memberMapper.toDomain(memberJpaEntity)))
                .getAccessToken();
    }
}
