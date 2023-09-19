package com.fivengers.blooming.project.application.service;

import com.fivengers.blooming.project.application.port.in.ConcertUseCase;
import com.fivengers.blooming.project.application.port.out.ConcertPort;
import com.fivengers.blooming.project.domain.Concert;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConcertService implements ConcertUseCase {

    private final ConcertPort concertPort;

    @Override
    public List<Concert> searchAll() {
        return concertPort.findAll();
    }

    @Override
    public List<Concert> searchAllOngoingProject() {
        return concertPort.findAllOngoingProject();
    }
}
