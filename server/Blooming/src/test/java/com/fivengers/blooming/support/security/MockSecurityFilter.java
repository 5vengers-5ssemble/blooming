package com.fivengers.blooming.support.security;

import com.fivengers.blooming.config.security.oauth2.LoginUser;
import com.fivengers.blooming.member.domain.AuthProvider;
import com.fivengers.blooming.member.domain.Member;
import com.fivengers.blooming.member.domain.MemberRole;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class MockSecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        Member member = createMember();

        LoginUser loginUser = new LoginUser(member,
                new HashMap<>(Map.of("id", member.getId())),
                member.getRole());

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        loginUser,
                        "password",
                        loginUser.getAuthorities()));

        chain.doFilter(request, response);
    }

    private Member createMember() {
        LocalDateTime now = LocalDateTime.now();
        return Member.builder()
                .id(1L)
                .oauthProvider(AuthProvider.KAKAO)
                .oauthAccount("1234567")
                .name("mock")
                .nickname("mock")
                .account("7654321")
                .createdAt(now)
                .modifiedAt(now)
                .role(List.of(MemberRole.ROLE_USER))
                .build();
    }


    @Override
    public void destroy() {
        SecurityContextHolder.clearContext();
    }
}
