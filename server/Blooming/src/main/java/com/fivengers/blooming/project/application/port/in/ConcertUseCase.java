package com.fivengers.blooming.project.application.port.in;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.project.domain.Concert;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConcertUseCase {

    Page<Concert> searchAll(Pageable pageable);
    Page<Concert> searchAllOngoingProject(Pageable pageable);
    Page<Concert> searchAllByArtist(Artist artist, Pageable pageable);
    List<Concert> searchAllFinishedProjectByArtist(Artist artist);
    Concert searchById(Long id);
}
