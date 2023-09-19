package com.fivengers.blooming.live.adapter.out.persistence.mapper;

import com.fivengers.blooming.live.adapter.out.persistence.entity.MemberJpaEntity;
import com.fivengers.blooming.live.domain.Member;
import org.springframework.stereotype.Component;

// TODO : merge 전 임시 클래스 --> 이후 삭제 예정

@Component
public class MemberMapper {

    public Member toDomain(MemberJpaEntity memberJpaEntity) {
        return new Member(memberJpaEntity.getId(), memberJpaEntity.getProfileImageUrl());
    }

    public MemberJpaEntity toJpaEntity(Member member) {
        return new MemberJpaEntity(member.getId(), member.getProfileImageUrl());

    }

}
