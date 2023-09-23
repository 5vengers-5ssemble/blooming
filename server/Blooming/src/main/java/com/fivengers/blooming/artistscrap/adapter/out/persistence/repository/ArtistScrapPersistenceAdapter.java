package com.fivengers.blooming.artistscrap.adapter.out.persistence.repository;

import com.fivengers.blooming.artistscrap.adapter.out.persistence.mapper.ArtistScrapMapper;
import com.fivengers.blooming.artistscrap.application.port.out.ArtistScrapPort;
import com.fivengers.blooming.artistscrap.domain.ArtistScrap;
import com.fivengers.blooming.global.exception.artistscrap.ArtistScrapNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArtistScrapPersistenceAdapter implements ArtistScrapPort {

    private final ArtistScrapSpringDataRepository artistScrapSpringDataRepository;
    private final ArtistScrapMapper artistScrapMapper;

    @Override
//    @Transactional
    public void saveScrap(ArtistScrap artistScrap) {
        artistScrapSpringDataRepository.save(artistScrapMapper.toJpaEntity(artistScrap));
    }

    @Override
    @Transactional
    public void deleteScrap(Long memberId, Long artistId) {
        artistScrapSpringDataRepository
                .deleteByMemberJpaEntityIdAndArtistJpaEntityId(memberId, artistId);
    }

    @Override
    public ArtistScrap findByMemberIdAndArtistId(Long memberId, Long artistId) {
        return artistScrapMapper.toDomain(artistScrapSpringDataRepository
                .findByMemberJpaEntityIdAndArtistJpaEntityId(memberId, artistId)
                .orElseThrow(ArtistScrapNotFoundException::new));
    }
}
