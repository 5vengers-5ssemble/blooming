package com.fivengers.blooming.member.application.port.in.dto;

import jakarta.validation.constraints.NotBlank;

public record MemberTokenRefreshRequest(@NotBlank String refreshToken) {

}
