package com.fivengers.blooming.member.adapter.in.web.dto;

import com.fivengers.blooming.member.domain.Member;

public record MemberResponse(Long id,
                             String name) {

    public static MemberResponse from(Member member) {
        return new MemberResponse(member.getId(), member.getName());
    }
}
