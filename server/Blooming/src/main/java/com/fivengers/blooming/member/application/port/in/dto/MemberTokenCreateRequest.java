package com.fivengers.blooming.member.application.port.in.dto;

import com.fivengers.blooming.member.domain.Member;
import com.fivengers.blooming.member.domain.MemberToken;

public record MemberTokenCreateRequest(String refreshToken,
                                       Member member) {

    public MemberToken toDomain() {
        return MemberToken.builder()
                .refreshToken(refreshToken)
                .member(member)
                .build();
    }
}
