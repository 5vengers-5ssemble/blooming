package com.fivengers.blooming.config.security.oauth2;

import com.fivengers.blooming.config.security.jwt.JwtProvider;
import com.fivengers.blooming.config.security.jwt.JwtSetupService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;

    @Value("${client.url}")
    private String clientUrl;

    @Value("${client.endpoint}")
    private String redirectEndPoint;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        getRedirectStrategy().sendRedirect(request, response,
                clientUrl + redirectEndPoint + "?token=" + getAuthToken(authentication));
        super.onAuthenticationSuccess(request, response, authentication);
    }

    private String getAuthToken(Authentication authentication) {
        return jwtProvider.createAuthToken((LoginUser) authentication.getPrincipal());
    }
}
