package com.fivengers.blooming.artistscrap.adapter.out.persistence.mapper;

import com.fivengers.blooming.artist.adapter.out.persistence.mapper.ArtistMapper;
import com.fivengers.blooming.artistscrap.adapter.out.persistence.entity.ArtistScrapRecordJpaEntity;
import com.fivengers.blooming.artistscrap.domain.ArtistScrapRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArtistScrapRecordMapper {

    private final ArtistMapper artistMapper;

    public ArtistScrapRecord toDomain(ArtistScrapRecordJpaEntity artistScrapRecordJpaEntity) {
        return ArtistScrapRecord.builder()
                .id(artistScrapRecordJpaEntity.getId())
                .scrapCount(artistScrapRecordJpaEntity.getScrapCount())
                .startDateOnWeek(artistScrapRecordJpaEntity.getStartDateOnWeek())
                .endDateOnWeek(artistScrapRecordJpaEntity.getEndDateOnWeek())
                .createdAt(artistScrapRecordJpaEntity.getCreatedAt())
                .modifiedAt(artistScrapRecordJpaEntity.getModifiedAt())
                .artist(artistMapper.toDomain(artistScrapRecordJpaEntity.getArtistJpaEntity()))
                .build();
    }

    public ArtistScrapRecordJpaEntity toJpaEntity(ArtistScrapRecord artistScrapRecord) {
        return ArtistScrapRecordJpaEntity.builder()
                .id(artistScrapRecord.getId())
                .scrapCount(artistScrapRecord.getScrapCount())
                .startDateOnWeek(artistScrapRecord.getStartDateOnWeek())
                .endDateOnWeek(artistScrapRecord.getEndDateOnWeek())
                .artistJpaEntity(artistMapper.toJpaEntity(artistScrapRecord.getArtist()))
                .build();
    }
}
