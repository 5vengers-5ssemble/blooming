package com.fivengers.blooming.artist.adapter.out.persistence.mapper;

import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistJpaEntity;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.member.adapter.out.persistence.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArtistMapper {

    private final MemberMapper memberMapper;

    public Artist toDomain(ArtistJpaEntity artistJpaEntity) {
        return Artist.builder()
                .id(artistJpaEntity.getId())
                .stageName(artistJpaEntity.getStageName())
                .agency(artistJpaEntity.getAgency())
                .description(artistJpaEntity.getDescription())
                .profileImageUrl(artistJpaEntity.getProfileImageUrl())
                .youtubeUrl(artistJpaEntity.getYoutubeUrl())
                .fanCafeUrl(artistJpaEntity.getFanCafeUrl())
                .snsUrl(artistJpaEntity.getSnsUrl())
                .createdAt(artistJpaEntity.getCreatedAt())
                .modifiedAt(artistJpaEntity.getModifiedAt())
                .member(memberMapper.toDomain(artistJpaEntity.getMemberJpaEntity()))
                .build();
    }

    public ArtistJpaEntity toJpaEntity(Artist artist) {
        return ArtistJpaEntity.builder()
                .id(artist.getId())
                .stageName(artist.getStageName())
                .agency(artist.getAgency())
                .description(artist.getDescription())
                .profileImageUrl(artist.getProfileImageUrl())
                .youtubeUrl(artist.getYoutubeUrl())
                .fanCafeUrl(artist.getFanCafeUrl())
                .snsUrl(artist.getSnsUrl())
                .deleted(false)
                .memberJpaEntity(memberMapper.toJpaEntity(artist.getMember()))
                .build();
    }

}
