package com.fivengers.blooming.artistscrap.adapter.out.persistence.mapper;

import com.fivengers.blooming.artist.adapter.out.persistence.mapper.ArtistMapper;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.artistscrap.adapter.out.persistence.entity.ArtistScrapJpaEntity;
import com.fivengers.blooming.artistscrap.domain.ArtistScrap;
import com.fivengers.blooming.member.adapter.out.persistence.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArtistScrapMapper {

    private final MemberMapper memberMapper;
    private final ArtistMapper artistMapper;

    public ArtistScrap toDomain(ArtistScrapJpaEntity artistScrapJpaEntity) {
        return ArtistScrap.builder()
                .id(artistScrapJpaEntity.getId())
                .member(memberMapper.toDomain(artistScrapJpaEntity.getMemberJpaEntity()))
                .artist(artistMapper.toDomain(artistScrapJpaEntity.getArtistJpaEntity()))
                .build();
    }

    public ArtistScrapJpaEntity toJpaEntity(ArtistScrap artistScrap) {
        return ArtistScrapJpaEntity.builder()
                .id(artistScrap.getId())
                .memberJpaEntity(memberMapper.toJpaEntity(artistScrap.getMember()))
                .artistJpaEntity(artistMapper.toJpaEntity(artistScrap.getArtist()))
                .build();
    }

}
