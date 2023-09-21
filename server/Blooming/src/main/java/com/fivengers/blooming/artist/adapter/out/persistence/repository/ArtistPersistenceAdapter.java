package com.fivengers.blooming.artist.adapter.out.persistence.repository;

import com.fivengers.blooming.artist.adapter.out.persistence.mapper.ArtistMapper;
import com.fivengers.blooming.artist.application.port.out.ArtistPort;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.global.exception.artist.ArtistNotFoundException;
import java.util.List;
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
    public List<Artist> findAll() {
        return artistSpringDataRepository.findByDeletedFalse().stream()
                .map(artistMapper::toDomain)
                .toList();
    }

    @Override
    public Artist findById(Long artistId) {
        return artistMapper.toDomain(artistSpringDataRepository.findById(artistId)
                .orElseThrow(ArtistNotFoundException::new));
    }

}
