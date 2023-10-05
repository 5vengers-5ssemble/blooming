package com.fivengers.blooming.member.adapter.in.web.dto;

import com.fivengers.blooming.member.domain.Member;
import com.fivengers.blooming.member.domain.MemberRole;
import java.util.List;

public record MemberResponse(Long artistId,
                             Long memberId,
                             String nickname,
                             List<MemberRole> role) {

    public static MemberResponse from(Long artistId, Member member) {
        return new MemberResponse(artistId, member.getId(), member.getNickname(), member.getRole());
    }
}
