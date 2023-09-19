package com.fivengers.blooming.member.adapter.in.web.dto;

import com.fivengers.blooming.live.domain.Member;
import lombok.Builder;

@Builder
public record MemberLiveProfileResponse (
        Long id,
        String profileImageUrl
) {

    public static MemberLiveProfileResponse from(Member member) {
        return MemberLiveProfileResponse.builder()
                .id(member.getId())
                .profileImageUrl(member.getProfileImageUrl())
                .build();
    }

}
