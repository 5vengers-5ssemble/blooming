package com.fivengers.blooming.nft.adapter.out.persistence.repository;

import com.fivengers.blooming.global.exception.nft.NftNotFoundException;
import com.fivengers.blooming.nft.adapter.out.persistence.mapper.NftMapper;
import com.fivengers.blooming.nft.domain.Nft;
import com.fivengers.blooming.nft.port.out.NftPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NftPersistenceAdapter implements NftPort {

    private final NftSpringDataRepository nftSpringDataRepository;
    private final NftMapper nftMapper;

    @Override
    public Nft save(Nft nft) {
        return nftMapper.toDomain(nftSpringDataRepository.save(nftMapper.toJpaEntity(nft)));
    }

    @Override
    public Nft findById(Long nftId) {
        return nftMapper.toDomain(nftSpringDataRepository.findById(nftId)
                .orElseThrow(NftNotFoundException::new));
    }
}
