package com.fivengers.blooming.member.adapter.in.web;

import static com.fivengers.blooming.support.docs.ApiDocumentUtils.getDocumentRequest;
import static com.fivengers.blooming.support.docs.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fivengers.blooming.artist.application.port.in.ArtistUseCase;
import com.fivengers.blooming.config.security.jwt.JwtProvider;
import com.fivengers.blooming.config.security.jwt.JwtToken;
import com.fivengers.blooming.config.security.jwt.JwtValidator;
import com.fivengers.blooming.config.security.oauth2.LoginUser;
import com.fivengers.blooming.member.application.port.in.MemberTokenUseCase;
import com.fivengers.blooming.member.application.port.in.dto.AuthRequest;
import com.fivengers.blooming.member.application.port.in.dto.MemberTokenRefreshRequest;
import com.fivengers.blooming.member.domain.AuthProvider;
import com.fivengers.blooming.member.domain.Member;
import com.fivengers.blooming.member.domain.MemberRole;
import com.fivengers.blooming.member.domain.MemberToken;
import com.fivengers.blooming.support.docs.RestDocsTest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(AuthController.class)
class AuthControllerTest extends RestDocsTest {

    @MockBean JwtValidator jwtValidator;
    @MockBean ArtistUseCase artistUseCase;
    @MockBean MemberTokenUseCase memberTokenUseCase;

    @Test
    @DisplayName("authToken으로 Jwt 정보를 가져온다.")
    void getJwtByAuthToken() throws Exception {
        AuthRequest request = new AuthRequest("authToken");
        LocalDateTime now = LocalDateTime.now();
        Member member = Member.builder()
                .id(1L)
                .oauthProvider(AuthProvider.KAKAO)
                .oauthAccount("12434512")
                .name("이지은")
                .nickname("아이유")
                .createdAt(now)
                .modifiedAt(now)
                .role(List.of(MemberRole.ROLE_USER))
                .build();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("id", member.getId());
        given(jwtValidator.findLoginUserByToken(any(String.class)))
                .willReturn(new LoginUser(member, attributes, member.getAuthority()));
        given(memberTokenUseCase.createJwtToken(any(LoginUser.class)))
                .willReturn(new JwtToken("accessToken", "refreshToken", "Bearer"));

        ResultActions perform = mockMvc.perform(post("/api/v1/auth")
                .content(toJson(request))
                .contentType(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isOk());

        perform.andDo(print())
                .andDo(document("auth",
                        getDocumentRequest(),
                        getDocumentResponse()));
    }

    @Test
    @DisplayName("refreshToken으로 Jwt 정보를 가져온다.")
    void getJwtByRefreshToken() throws Exception {
        MemberTokenRefreshRequest request = new MemberTokenRefreshRequest("refreshToken");
        LocalDateTime now = LocalDateTime.now();
        Member member = Member.builder()
                .id(1L)
                .oauthProvider(AuthProvider.KAKAO)
                .oauthAccount("12434512")
                .name("이지은")
                .nickname("아이유")
                .createdAt(now)
                .modifiedAt(now)
                .role(List.of(MemberRole.ROLE_USER))
                .build();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("id", member.getId());
        given(jwtValidator.findLoginUserByToken(any(String.class)))
                .willReturn(new LoginUser(member, attributes, member.getAuthority()));
        given(jwtValidator.findLoginUserByToken(any(String.class)))
                .willReturn(new LoginUser(member, attributes, member.getAuthority()));
        given(memberTokenUseCase.createJwtToken(any(LoginUser.class)))
                .willReturn(new JwtToken("accessToken", "refreshToken", "Bearer"));
        given(memberTokenUseCase.findByRefreshToken(any(String.class)))
                .willReturn(new MemberToken(1L, "refreshToken", LocalDateTime.now(),
                        LocalDateTime.now(), member));
        given(memberTokenUseCase.refresh(any(MemberTokenRefreshRequest.class),
                any(MemberToken.class)))
                .willReturn(new JwtToken("accessToken", "refreshToken", "Bearer"));

        ResultActions perform = mockMvc.perform(post("/api/v1/auth/refresh")
                .content(toJson(request))
                .contentType(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isOk());

        perform.andDo(print())
                .andDo(document("auth-refresh",
                        getDocumentRequest(),
                        getDocumentResponse()));
    }
}