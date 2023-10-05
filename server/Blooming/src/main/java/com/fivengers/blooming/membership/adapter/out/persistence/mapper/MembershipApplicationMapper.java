package com.fivengers.blooming.membership.adapter.out.persistence.mapper;

import com.fivengers.blooming.artist.adapter.out.persistence.mapper.ArtistMapper;
import com.fivengers.blooming.membership.adapter.out.persistence.entity.MembershipApplicationJpaEntity;
import com.fivengers.blooming.membership.domain.MembershipApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MembershipApplicationMapper {

    private final ArtistMapper artistMapper;

    public MembershipApplication toDomain(MembershipApplicationJpaEntity jpaEntity) {
        return MembershipApplication.builder()
                .id(jpaEntity.getId())
                .title(jpaEntity.getTitle())
                .description(jpaEntity.getDescription())
                .seasonStart(jpaEntity.getSeasonStart())
                .seasonEnd(jpaEntity.getSeasonEnd())
                .purchaseStart(jpaEntity.getPurchaseStart())
                .purchaseEnd(jpaEntity.getPurchaseEnd())
                .saleCount(jpaEntity.getSaleCount())
                .salePrice(jpaEntity.getSalePrice())
                .thumbnailUrl(jpaEntity.getThumbnailUrl())
                .baseUri(jpaEntity.getBaseUri())
                .privateKey(jpaEntity.getPrivateKey())
                .applicationState(jpaEntity.getApplicationState())
                .createdAt(jpaEntity.getCreatedAt())
                .modifiedAt(jpaEntity.getModifiedAt())
                .artist(artistMapper.toDomain(jpaEntity.getArtistJpaEntity()))
                .build();
    }

    public MembershipApplicationJpaEntity toJpaEntity(MembershipApplication application) {
        return MembershipApplicationJpaEntity.builder()
                .id(application.getId())
                .title(application.getTitle())
                .description(application.getDescription())
                .seasonStart(application.getSeasonStart())
                .seasonEnd(application.getSeasonEnd())
                .purchaseStart(application.getPurchaseStart())
                .purchaseEnd(application.getPurchaseEnd())
                .saleCount(application.getSaleCount())
                .salePrice(application.getSalePrice())
                .thumbnailUrl(application.getThumbnailUrl())
                .baseUri(application.getBaseUri())
                .privateKey(application.getPrivateKey())
                .applicationState(application.getApplicationState())
                .deleted(false)
                .artistJpaEntity(artistMapper.toJpaEntity(application.getArtist()))
                .build();
    }
}
