package com.fivengers.blooming.nft.adapter.out.persistence.mapper;

import com.fivengers.blooming.artist.adapter.out.persistence.mapper.ArtistMapper;
import com.fivengers.blooming.nft.adapter.out.persistence.entity.NftJpaEntity;
import com.fivengers.blooming.nft.domain.Nft;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NftMapper {

    private final ArtistMapper artistMapper;

    public Nft toDomain(NftJpaEntity nftJpaEntity) {
        return Nft.builder()
                .id(nftJpaEntity.getId())
                .tokenId(nftJpaEntity.getTokenId())
                .contractAddress(nftJpaEntity.getContractAddress())
                .symbol(nftJpaEntity.getSymbol())
                .createdAt(nftJpaEntity.getCreatedAt())
                .modifiedAt(nftJpaEntity.getModifiedAt())
                .artist(artistMapper.toDomain(nftJpaEntity.getArtist()))
                .build();
    }

    public NftJpaEntity toJpaEntity(Nft nft) {
        return NftJpaEntity.builder()
                .tokenId(nft.getTokenId())
                .contractAddress(nft.getContractAddress())
                .symbol(nft.getSymbol())
                .deleted(false)
                .artist(artistMapper.toJpaEntity(nft.getArtist()))
                .build();
    }
}
