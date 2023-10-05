package com.fivengers.blooming.member.adapter.in.web.dto;

import com.fivengers.blooming.member.domain.AuthProvider;
import com.fivengers.blooming.member.domain.Member;
import com.fivengers.blooming.member.domain.MemberRole;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public record MemberDetailsResponse(Long id,
                                    String oauthAccount,
                                    AuthProvider oauthProvider,
                                    String name,
                                    String nickname,
                                    LocalDateTime createdAt,
                                    LocalDateTime modifiedAt,
                                    List<MemberRole> role) {

    public static MemberDetailsResponse from(Member member) {
        return MemberDetailsResponse.builder()
                .id(member.getId())
                .oauthAccount(member.getOauthAccount())
                .oauthProvider(member.getOauthProvider())
                .name(member.getName())
                .nickname(member.getNickname())
                .createdAt(member.getCreatedAt())
                .modifiedAt(member.getModifiedAt())
                .role(member.getRole())
                .build();
    }
}
