package com.fivengers.blooming.membership.adapter.out.persistence.mapper;

import com.fivengers.blooming.artist.adapter.out.persistence.mapper.ArtistMapper;
import com.fivengers.blooming.membership.adapter.out.persistence.entity.MembershipJpaEntity;
import com.fivengers.blooming.membership.adapter.out.persistence.entity.NftSaleJpaEntity;
import com.fivengers.blooming.membership.domain.Membership;
import com.fivengers.blooming.membership.domain.NftSale;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MembershipMapper {

    private final ArtistMapper artistMapper;

    public Membership toDomain(MembershipJpaEntity membershipJpaEntity) {
        return Membership.builder()
                .id(membershipJpaEntity.getId())
                .title(membershipJpaEntity.getTitle())
                .symbol(membershipJpaEntity.getSymbol())
                .description(membershipJpaEntity.getDescription())
                .season(membershipJpaEntity.getSeason())
                .seasonStart(membershipJpaEntity.getSeasonStart())
                .seasonEnd(membershipJpaEntity.getSeasonEnd())
                .purchaseStart(membershipJpaEntity.getPurchaseStart())
                .purchaseEnd(membershipJpaEntity.getPurchaseEnd())
                .saleCount(membershipJpaEntity.getSaleCount())
                .salePrice(membershipJpaEntity.getSalePrice())
                .thumbnailUrl(membershipJpaEntity.getThumbnailUrl())
                .baseUri(membershipJpaEntity.getBaseUri())
                .contractAddress(membershipJpaEntity.getContractAddress())
                .createdAt(membershipJpaEntity.getCreatedAt())
                .modifiedAt(membershipJpaEntity.getModifiedAt())
                .artist(artistMapper.toDomain(membershipJpaEntity.getArtistJpaEntity()))
                .nftSale(toNftSaleDomain(membershipJpaEntity.getNftSaleJpaEntity()))
                .build();
    }

    public MembershipJpaEntity toJpaEntity(Membership membership) {
        return MembershipJpaEntity.builder()
                .id(membership.getId())
                .title(membership.getTitle())
                .symbol(membership.getSymbol())
                .description(membership.getDescription())
                .season(membership.getSeason())
                .seasonStart(membership.getSeasonStart())
                .seasonEnd(membership.getSeasonEnd())
                .purchaseStart(membership.getPurchaseStart())
                .purchaseEnd(membership.getPurchaseEnd())
                .saleCount(membership.getSaleCount())
                .salePrice(membership.getSalePrice())
                .thumbnailUrl(membership.getThumbnailUrl())
                .baseUri(membership.getBaseUri())
                .contractAddress(membership.getContractAddress())
                .deleted(false)
                .artistJpaEntity(artistMapper.toJpaEntity(membership.getArtist()))
                .nftSaleJpaEntity(toNftSaleJpaEntity(membership.getNftSale()))
                .build();
    }

    private NftSale toNftSaleDomain(NftSaleJpaEntity nftSaleJpaEntity) {
        return NftSale.builder()
                .id(nftSaleJpaEntity.getId())
                .totalNftCount(nftSaleJpaEntity.getTotalNftCount())
                .soldNftCount(nftSaleJpaEntity.getSoldNftCount())
                .totalNftAmount(nftSaleJpaEntity.getTotalNftAmount())
                .soldNftAmount(nftSaleJpaEntity.getSoldNftAmount())
                .build();
    }

    private NftSaleJpaEntity toNftSaleJpaEntity(NftSale nftSale) {
        return NftSaleJpaEntity.builder()
                .id(nftSale.getId())
                .totalNftCount(nftSale.getTotalNftCount())
                .soldNftCount(nftSale.getSoldNftCount())
                .totalNftAmount(nftSale.getTotalNftAmount())
                .soldNftAmount(nftSale.getSoldNftAmount())
                .deleted(false)
                .build();
    }
}
