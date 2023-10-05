package com.fivengers.blooming.member.adapter.out.persistence.mapper;

import com.fivengers.blooming.member.adapter.out.persistence.entity.MemberTokenJpaEntity;
import com.fivengers.blooming.member.domain.MemberToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberTokenMapper {

    private final MemberMapper memberMapper;

    public MemberToken toDomain(MemberTokenJpaEntity memberTokenJpaEntity) {
        return MemberToken.builder()
                .id(memberTokenJpaEntity.getId())
                .refreshToken(memberTokenJpaEntity.getRefreshToken())
                .createdAt(memberTokenJpaEntity.getCreatedAt())
                .modifiedAt(memberTokenJpaEntity.getModifiedAt())
                .member(memberMapper.toDomain(memberTokenJpaEntity.getMemberJpaEntity()))
                .build();
    }

    public MemberTokenJpaEntity toJpaEntity(MemberToken memberToken) {
        return MemberTokenJpaEntity.builder()
                .id(memberToken.getId())
                .refreshToken(memberToken.getRefreshToken())
                .memberJpaEntity(memberMapper.toJpaEntity(memberToken.getMember()))
                .build();
    }
}
