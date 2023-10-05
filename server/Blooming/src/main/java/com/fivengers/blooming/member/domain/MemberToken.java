package com.fivengers.blooming.member.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberToken {

    private Long id;
    private String refreshToken;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Member member;

    @Builder
    public MemberToken(Long id, String refreshToken, LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            Member member) {
        this.id = id;
        this.refreshToken = refreshToken;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.member = member;
    }

    public void modify(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
