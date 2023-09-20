package com.fivengers.blooming.project.adapter.out.persistence.mapper;


import com.fivengers.blooming.artist.adapter.out.persistence.mapper.ArtistMapper;
import com.fivengers.blooming.project.adapter.out.persistence.entity.ConcertJpaEntity;
import com.fivengers.blooming.project.domain.Concert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConcertMapper {

    private final ArtistMapper artistMapper;

    public Concert toDomain(ConcertJpaEntity concertJpaEntity) {
        return Concert.builder()
                .id(concertJpaEntity.getId())
                .name(concertJpaEntity.getName())
                .fundingAmount(concertJpaEntity.getFundingAmount())
                .targetAmount(concertJpaEntity.getTargetAmount())
                .startedAt(concertJpaEntity.getStartedAt())
                .endedAt(concertJpaEntity.getEndedAt())
                .description(concertJpaEntity.getDescription())
                .createdAt(concertJpaEntity.getCreatedAt())
                .modifiedAt(concertJpaEntity.getModifiedAt())
                .artist(artistMapper.toDomain(concertJpaEntity.getArtist()))
                .build();
    }

    public ConcertJpaEntity toJpaEntity(Concert concert) {
        return ConcertJpaEntity.builder()
                .id(concert.getId())
                .fundingAmount(concert.getFundingAmount())
                .targetAmount(concert.getTargetAmount())
                .startedAt(concert.getStartedAt())
                .endedAt(concert.getEndedAt())
                .description(concert.getDescription())
                .artist(artistMapper.toJpaEntity(concert.getArtist()))
                .build();
    }
}
