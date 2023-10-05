package com.fivengers.blooming.artist.adapter.out.persistence.repository;

import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistVideoJpaEntity;
import com.fivengers.blooming.artist.adapter.out.persistence.mapper.ArtistVideoMapper;
import com.fivengers.blooming.artist.application.port.out.ArtistVideoPort;
import com.fivengers.blooming.artist.domain.ArtistVideo;
import com.fivengers.blooming.global.exception.artistvideo.ArtistVideoNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArtistVideoPersistenceAdapter implements ArtistVideoPort {

    private final ArtistVideoSpringDataRepository artistVideoSpringDataRepository;
    private final ArtistVideoMapper artistVideoMapper;

    @Override
    public Optional<ArtistVideo> findById(Long artistVideoId) {
        return artistVideoSpringDataRepository.findById(artistVideoId)
                .map(artistVideoMapper::toDomain);
    }

    @Override
    public List<ArtistVideo> findByArtistId(Long artistId) {
        return artistVideoSpringDataRepository.findByArtistJpaEntityId(artistId).stream()
                .map(artistVideoMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public ArtistVideo save(ArtistVideo artistVideo) {
        return artistVideoMapper.toDomain(artistVideoSpringDataRepository
                .save(artistVideoMapper.toJpaEntity(artistVideo)));
    }

    @Override
    @Transactional
    public ArtistVideo update(ArtistVideo artistVideo) {
        ArtistVideoJpaEntity artistVideoJpaEntity = artistVideoSpringDataRepository
                .findById(artistVideo.getId())
                .orElseThrow(ArtistVideoNotFoundException::new);

        artistVideoJpaEntity.update(artistVideo);
        return artistVideoMapper.toDomain(artistVideoJpaEntity);
    }

    @Override
    @Transactional
    public void deleteById(Long artistVideoId) {
        artistVideoSpringDataRepository.findById(artistVideoId)
                .orElseThrow(ArtistVideoNotFoundException::new)
                .delete();
    }
}
