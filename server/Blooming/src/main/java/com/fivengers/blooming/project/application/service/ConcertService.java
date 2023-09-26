package com.fivengers.blooming.project.application.service;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.project.application.port.in.ConcertUseCase;
import com.fivengers.blooming.project.application.port.out.ConcertPort;
import com.fivengers.blooming.project.domain.Concert;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<Concert> searchAll(Pageable pageable, List<Concert> exclusions) {
        return concertPort.findAll(pageable, exclusions);
    }

    @Override
    public Page<Concert> searchAllOngoingProject(Pageable pageable) {
        return concertPort.findAllOngoingProject(pageable);
    }

    @Override
    public Page<Concert> searchAllOngoingProject(Pageable pageable, List<Concert> exclusions) {
        return concertPort.findAllOngoingProject(pageable, exclusions);
    }

    @Override
    public List<Concert> searchAllFinishedProjectByArtist(Long artistId) {
        return concertPort.findAllFinishedProjectByArtist(artistId,
                PageRequest.of(0, 5, Sort.by("createdAt").descending()));
    }

    @Override
    public Concert searchById(Long id) {
        return concertPort.findById(id);
    }

    @Override
    public Page<Concert> searchAllByLikeKeyword(String keyword, Pageable pageable) {
        return concertPort.findAllByLikeKeyword(keyword, pageable);
    }

    @Override
    public Page<Concert> searchAllByLikeArtist(String artist, Pageable pageable) {
        return concertPort.findAllByLikeArtist(artist, pageable);
    }
}
