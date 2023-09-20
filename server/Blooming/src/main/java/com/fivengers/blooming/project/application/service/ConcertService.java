package com.fivengers.blooming.project.application.service;

import com.fivengers.blooming.project.application.port.in.ConcertUseCase;
import com.fivengers.blooming.project.application.port.out.ConcertPort;
import com.fivengers.blooming.project.domain.Concert;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConcertService implements ConcertUseCase {

    private final ConcertPort concertPort;

    @Override
    public Page<Concert> searchAll(Pageable pageable) {
        return concertPort.findAll(pageable);
    }

    @Override
    public Page<Concert> searchAllOngoingProject(Pageable pageable) {
        return concertPort.findAllOngoingProject(pageable);
    }
}
