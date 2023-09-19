package com.fivengers.blooming.live.adapter.out.persistence.mapper;

// TODO : merge 전 임시 클래스 --> 이후 삭제 예정

import com.fivengers.blooming.live.adapter.out.persistence.entity.ArtistJpaEntity;
import com.fivengers.blooming.live.domain.Artist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArtistMapper {

    private final MemberMapper memberMapper;

    public Artist toDomain(ArtistJpaEntity artistJpaEntity) {
        return new Artist(artistJpaEntity.getId(),
                artistJpaEntity.getStageName(),
                memberMapper.toDomain(artistJpaEntity.getMemberJpaEntity()));
    }

    public ArtistJpaEntity toJpaEntity(Artist artist) {
        return new ArtistJpaEntity(artist.getId(),
                artist.getStageName(),
                memberMapper.toJpaEntity(artist.getMember()));
    }
}
