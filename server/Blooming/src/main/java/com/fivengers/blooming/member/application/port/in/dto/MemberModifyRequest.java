package com.fivengers.blooming.member.application.port.in.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record MemberModifyRequest(@NotBlank @Length(max = 10) String nickname) {
}
