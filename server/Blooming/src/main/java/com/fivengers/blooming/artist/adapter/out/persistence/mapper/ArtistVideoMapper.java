package com.fivengers.blooming.artist.adapter.out.persistence.mapper;

import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistVideoJpaEntity;
import com.fivengers.blooming.artist.domain.ArtistVideo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArtistVideoMapper {

    private final ArtistMapper artistMapper;

    public ArtistVideo toDomain(ArtistVideoJpaEntity artistVideoJpaEntity) {
        return ArtistVideo.builder()
                .id(artistVideoJpaEntity.getId())
                .videoUrl(artistVideoJpaEntity.getVideoUrl())
                .artist(artistMapper.toDomain(artistVideoJpaEntity.getArtistJpaEntity()))
                .build();
    }

    public ArtistVideoJpaEntity toJpaEntity(ArtistVideo artistVideo) {
        return ArtistVideoJpaEntity.builder()
                .id(artistVideo.getId())
                .videoUrl(artistVideo.getVideoUrl())
                .artistJpaEntity(artistMapper.toJpaEntity(artistVideo.getArtist()))
                .deleted(false)
                .build();
    }
}
