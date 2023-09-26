package com.fivengers.blooming.artist.adapter.out.persistence.repository;

import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistJpaEntity;
import com.fivengers.blooming.artist.adapter.out.persistence.mapper.ArtistMapper;
import com.fivengers.blooming.artist.application.port.out.ArtistPort;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.global.exception.artist.ArtistNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArtistPersistenceAdapter implements ArtistPort {

    private final ArtistMapper artistMapper;
    private final ArtistSpringDataRepository artistSpringDataRepository;

    @Override
    @Transactional
    public Artist save(Artist artist) {
        return artistMapper.toDomain(
                artistSpringDataRepository.save(artistMapper.toJpaEntity(artist)));
    }

    @Override
    public List<Artist> findAll() {
        return artistSpringDataRepository.findByDeletedFalse().stream()
                .map(artistMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Artist> findById(Long artistId) {
        return artistSpringDataRepository.findById(artistId).map(artistMapper::toDomain);
    }

    @Override
    @Transactional
    public Artist update(Artist artist) {
        ArtistJpaEntity artistJpaEntity = artistSpringDataRepository.findById(artist.getId())
                .orElseThrow(ArtistNotFoundException::new);
        artistJpaEntity.update(artist);
        return artistMapper.toDomain(artistJpaEntity);
    }

}
