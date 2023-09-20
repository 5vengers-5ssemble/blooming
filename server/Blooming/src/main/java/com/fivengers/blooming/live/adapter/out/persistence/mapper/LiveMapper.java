package com.fivengers.blooming.live.adapter.out.persistence.mapper;

import com.fivengers.blooming.artist.adapter.out.persistence.mapper.ArtistMapper;
import com.fivengers.blooming.live.adapter.out.persistence.entity.LiveJpaEntity;
import com.fivengers.blooming.live.domain.Live;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LiveMapper {

    private final ArtistMapper artistMapper;

    public Live toDomain(LiveJpaEntity liveJpaEntity) {
        return Live.builder()
                .id(liveJpaEntity.getId())
                .title(liveJpaEntity.getTitle())
                .artist(artistMapper.toDomain(liveJpaEntity.getArtistJpaEntity()))
                .createdAt(liveJpaEntity.getCreatedAt())
                .modifiedAt(liveJpaEntity.getModifiedAt())
                .build();
    }

    public LiveJpaEntity toJpaEntity(Live live) {
        return LiveJpaEntity.builder()
                .id(live.getId())
                .title(live.getTitle())
                .artistJpaEntity(artistMapper.toJpaEntity(live.getArtist()))
                .endedAt(live.getEndedAt())
                .build();
    }

}
