package com.fivengers.blooming.artist.adapter.out.persistence.repository;

import com.fivengers.blooming.artist.adapter.out.persistence.mapper.ArtistVideoMapper;
import com.fivengers.blooming.artist.application.port.out.ArtistVideoPort;
import com.fivengers.blooming.artist.domain.ArtistVideo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ArtistVideoPersistenceAdapter implements ArtistVideoPort {

    private final ArtistVideoSpringDataRepository artistVideoSpringDataRepository;
    private final ArtistVideoMapper artistVideoMapper;

    @Override
    public List<ArtistVideo> findByArtistId(Long artistId) {
        return artistVideoSpringDataRepository.findByArtistJpaEntityId(artistId).stream()
                .map(artistVideoMapper::toDomain)
                .toList();
    }

    @Override
    public ArtistVideo save(ArtistVideo artistVideo) {
        return artistVideoMapper.toDomain(
                artistVideoSpringDataRepository.save(artistVideoMapper.toJpaEntity(artistVideo)));
    }
}
