package com.fivengers.blooming.live.domain;

// TODO : merge 전 임시 클래스 --> 이후 삭제 예정

import lombok.Getter;

@Getter
public class Member {

    private Long id;
    private String profileImageUrl;

    public Member(Long id, String profileImageUrl) {
        this.id = id;
        this.profileImageUrl = profileImageUrl;
    }
}
