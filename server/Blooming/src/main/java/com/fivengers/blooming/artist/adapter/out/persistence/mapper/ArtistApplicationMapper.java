package com.fivengers.blooming.artist.adapter.out.persistence.mapper;

import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistApplicationJpaEntity;
import com.fivengers.blooming.artist.domain.ArtistApplication;
import com.fivengers.blooming.member.adapter.out.persistence.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArtistApplicationMapper {

    private final MemberMapper memberMapper;

    public ArtistApplication toDomain(ArtistApplicationJpaEntity jpaEntity) {
        return ArtistApplication.builder()
                .id(jpaEntity.getId())
                .stageName(jpaEntity.getStageName())
                .description(jpaEntity.getDescription())
                .agency(jpaEntity.getAgency())
                .applicationState(jpaEntity.getApplicationState())
                .profileImageUrl(jpaEntity.getProfileImageUrl())
                .youtubeUrl(jpaEntity.getYoutubeUrl())
                .fanCafeUrl(jpaEntity.getFanCafeUrl())
                .snsUrl(jpaEntity.getSnsUrl())
                .createdAt(jpaEntity.getCreatedAt())
                .modifiedAt(jpaEntity.getModifiedAt())
                .member(memberMapper.toDomain(jpaEntity.getMemberJpaEntity()))
                .build();
    }

    public ArtistApplicationJpaEntity toJpaEntity(ArtistApplication artistApplication) {
        return ArtistApplicationJpaEntity.builder()
                .stageName(artistApplication.getStageName())
                .description(artistApplication.getDescription())
                .agency(artistApplication.getAgency())
                .applicationState(artistApplication.getApplicationState())
                .profileImageUrl(artistApplication.getProfileImageUrl())
                .youtubeUrl(artistApplication.getYoutubeUrl())
                .fanCafeUrl(artistApplication.getFanCafeUrl())
                .snsUrl(artistApplication.getSnsUrl())
                .memberJpaEntity(memberMapper.toJpaEntity(artistApplication.getMember()))
                .build();
    }
}
