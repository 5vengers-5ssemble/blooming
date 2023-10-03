package com.fivengers.blooming.project.adapter.out.persistence.repository;


import com.fivengers.blooming.global.exception.project.ProjectNotFoundException;
import com.fivengers.blooming.project.adapter.out.persistence.entity.ConcertJpaEntity;
import com.fivengers.blooming.project.adapter.out.persistence.mapper.ConcertMapper;
import com.fivengers.blooming.project.application.port.out.ConcertPort;
import com.fivengers.blooming.project.domain.Concert;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ConcertPersistenceAdapter implements ConcertPort {

    private final ConcertMapper concertMapper;
    private final ConcertSpringDataRepository concertSpringDataRepository;

    @Override
    public Page<Concert> findAll(Pageable pageable) {
        Page<ConcertJpaEntity> concerts = concertSpringDataRepository.findAll(pageable);
        return new PageImpl<>(concerts.stream()
                .map(concertMapper::toDomain)
                .toList(), pageable, concerts.getTotalElements());
    }

    @Override
    public Page<Concert> findAllOngoingProject(Pageable pageable) {
        Page<ConcertJpaEntity> concerts = concertSpringDataRepository
                .findByEndedAtIsAfter(pageable, LocalDateTime.now());
        return new PageImpl<>(concerts.stream()
                .map(concertMapper::toDomain)
                .toList(), pageable, concerts.getTotalElements());
    }

    @Override
    public List<Concert> findAllFinishedProjectByArtist(Long artistId) {
        return concertSpringDataRepository.findAllFinishedProjectByArtist(artistId)
                .stream()
                .map(concertMapper::toDomain)
                .toList();
    }

    @Override
    public List<Concert> findBestThreeProject() {
        return concertSpringDataRepository.findBestThreeProject().stream()
                .map(concertMapper::toDomain)
                .toList();
    }

    @Override
    public Concert findById(Long id) {
        return concertSpringDataRepository.findById(id)
                .map(concertMapper::toDomain)
                .orElseThrow(ProjectNotFoundException::new);
    }

    @Override
    public Page<Concert> findAllByLikeKeyword(String keyword, Pageable pageable) {
        Page<ConcertJpaEntity> concerts = concertSpringDataRepository.findAllByLikeKeyword(keyword,
                pageable);
        return new PageImpl<>(concerts.stream()
                .map(concertMapper::toDomain)
                .toList(), pageable, concerts.getTotalElements());
    }

    @Override
    public Page<Concert> findAllByLikeArtist(String artist, Pageable pageable) {
        Page<ConcertJpaEntity> concerts = concertSpringDataRepository.findAllByLikeArtist(
                artist, pageable);
        return new PageImpl<>(concerts.stream()
                .map(concertMapper::toDomain)
                .toList(), pageable, concerts.getTotalElements());
    }

    @Override
    public Optional<Concert> findByArtistId(Long artistId) {
        return concertSpringDataRepository.findConcertByArtistId(artistId)
                .map(concertMapper::toDomain);
    }
}
