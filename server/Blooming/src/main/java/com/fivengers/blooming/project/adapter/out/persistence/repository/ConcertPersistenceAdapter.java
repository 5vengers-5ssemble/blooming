package com.fivengers.blooming.project.adapter.out.persistence.repository;


import com.fivengers.blooming.project.adapter.out.persistence.entity.ConcertJpaEntity;
import com.fivengers.blooming.project.adapter.out.persistence.mapper.ConcertMapper;
import com.fivengers.blooming.project.application.port.out.ConcertPort;
import com.fivengers.blooming.project.domain.Concert;
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
        Page<ConcertJpaEntity> concerts = concertSpringDataRepository.findAllOngoingProject(pageable);
        return new PageImpl<>(concerts.stream()
                .map(concertMapper::toDomain)
                .toList(), pageable, concerts.getTotalElements());
    }
}
