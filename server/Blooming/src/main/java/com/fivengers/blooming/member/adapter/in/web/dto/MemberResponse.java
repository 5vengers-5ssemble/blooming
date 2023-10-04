package com.fivengers.blooming.member.adapter.in.web.dto;

import com.fivengers.blooming.member.domain.Member;
import com.fivengers.blooming.member.domain.MemberRole;
import java.util.List;

public record MemberResponse(Long id,
                             String nickname,
                             List<MemberRole> role) {

    public static MemberResponse from(Long id, Member member) {
        return new MemberResponse(id, member.getNickname(), member.getRole());
    }
}
