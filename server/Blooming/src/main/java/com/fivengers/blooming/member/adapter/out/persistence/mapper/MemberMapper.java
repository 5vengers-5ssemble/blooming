package com.fivengers.blooming.member.adapter.out.persistence.mapper;

import com.fivengers.blooming.member.adapter.out.persistence.entity.MemberJpaEntity;
import com.fivengers.blooming.member.adapter.out.persistence.entity.Oauth;
import com.fivengers.blooming.member.domain.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public Member toDomain(MemberJpaEntity memberJpaEntity) {
        return Member.builder()
                .id(memberJpaEntity.getId())
                .oauthProvider(memberJpaEntity.getOauth().getOauthProvider())
                .oauthAccount(memberJpaEntity.getOauth().getOauthAccount())
                .name(memberJpaEntity.getName())
                .nickname(memberJpaEntity.getNickname())
                .createdAt(memberJpaEntity.getCreatedAt())
                .modifiedAt(memberJpaEntity.getModifiedAt())
                .role(memberJpaEntity.getRole())
                .build();
    }

    public MemberJpaEntity toJpaEntity(Member member) {
        return MemberJpaEntity.builder()
                .id(member.getId())
                .oauth(new Oauth(member.getOauthProvider(), member.getOauthAccount()))
                .name(member.getName())
                .nickname(member.getNickname())
                .deleted(false)
                .build();
    }
}
