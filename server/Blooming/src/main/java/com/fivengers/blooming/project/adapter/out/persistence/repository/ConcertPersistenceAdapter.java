package com.fivengers.blooming.project.adapter.out.persistence.repository;


import com.fivengers.blooming.project.adapter.out.persistence.mapper.ConcertMapper;
import com.fivengers.blooming.project.application.port.out.ConcertPort;
import com.fivengers.blooming.project.domain.Concert;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
    public List<Concert> findAll(Pageable pageable) {
        return concertSpringDataRepository.findAll(pageable).stream()
                .map(concertMapper::toDomain)
                .toList();
    }

    @Override
    public List<Concert> findAllOngoingProject(Pageable pageable) {
        return concertSpringDataRepository.findAllOngoingProject(pageable).stream()
                .map(concertMapper::toDomain)
                .toList();
    }
}
