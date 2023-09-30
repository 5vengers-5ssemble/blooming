package com.fivengers.blooming.artist.adapter.out.persistence.repository;

import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistApplicationJpaEntity;
import com.fivengers.blooming.artist.adapter.out.persistence.mapper.ArtistApplicationMapper;
import com.fivengers.blooming.artist.application.port.out.ArtistApplicationPort;
import com.fivengers.blooming.artist.domain.ArtistApplication;
import com.fivengers.blooming.artist.domain.ArtistApplicationState;
import com.fivengers.blooming.global.exception.artist.ArtistApplicationNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArtistApplicationAdapter implements ArtistApplicationPort {

    private final ArtistApplicationMapper artistApplicationMapper;
    private final ArtistApplicationSpringDataRepository artistApplicationSpringDataRepository;
    private final ArtistApplicationQueryRepository artistApplicationQueryRepository;

    @Override
    public Page<ArtistApplication> findByArtistApplicationState(Pageable pageable,
            ArtistApplicationState state) {
        Page<ArtistApplicationJpaEntity> states = artistApplicationQueryRepository
                .findByArtistApplicationState(pageable, state);
        return new PageImpl<>(states.getContent().stream()
                .map(artistApplicationMapper::toDomain)
                .toList(), pageable, states.getTotalElements());
    }

    @Override
    @Transactional
    public ArtistApplication save(ArtistApplication artistApplication) {
        return artistApplicationMapper.toDomain(
                artistApplicationSpringDataRepository.save(
                        artistApplicationMapper.toJpaEntity(artistApplication)));
    }

    @Override
    public Optional<ArtistApplication> findById(Long applicationId) {
        return artistApplicationQueryRepository.findById(applicationId)
                .map(artistApplicationMapper::toDomain);
    }

    @Override
    public Optional<ArtistApplication> findByMemberId(Long memberId) {
        return artistApplicationQueryRepository.findByMemberId(memberId)
                .map(artistApplicationMapper::toDomain);
    }

    @Override
    @Transactional
    public ArtistApplication update(ArtistApplication artistApplication) {
        ArtistApplicationJpaEntity jpaEntity =
                artistApplicationSpringDataRepository.findById(artistApplication.getId())
                        .orElseThrow(ArtistApplicationNotFoundException::new);

        jpaEntity.update(artistApplication);
        return artistApplicationMapper.toDomain(jpaEntity);
    }
}
