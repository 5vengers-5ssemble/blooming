package com.fivengers.blooming.live.domain;

// TODO : merge 전 임시 클래스 --> 이후 삭제 예정

import lombok.Builder;
import lombok.Getter;

@Getter
public class Artist {

    private Long id;
    private String stageName;
    private Member member;

    @Builder
    public Artist(Long id, String stageName, Member member) {
        this.id = id;
        this.stageName = stageName;
        this.member = member;
    }
}
