package com.fivengers.blooming.member.adapter.in.web;

import com.fivengers.blooming.config.security.jwt.JwtProvider;
import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.member.adapter.in.web.dto.AuthResponse;
import com.fivengers.blooming.member.application.port.in.dto.AuthRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtProvider jwtProvider;

    @PostMapping
    public ApiResponse<AuthResponse> authTokenDetails(@RequestBody @Validated AuthRequest authRequest) {
        return ApiResponse.ok(
                AuthResponse.from(jwtProvider.createJwtTokenByAuthToken(authRequest.authToken())));
    }
}
