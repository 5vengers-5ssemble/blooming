package com.fivengers.blooming.member.domain;

import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public Member toDomain(MemberJpaEntity memberJpaEntity) {
        return new Member(memberJpaEntity.getId(), memberJpaEntity.getName());
    }

    public MemberJpaEntity toEntity(Member member){
        return new MemberJpaEntity(member.getId(), member.getName());
    }

}
