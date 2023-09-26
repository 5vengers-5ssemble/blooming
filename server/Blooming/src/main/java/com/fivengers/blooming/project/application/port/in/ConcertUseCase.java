package com.fivengers.blooming.project.application.port.in;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.project.domain.Concert;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConcertUseCase {

    Page<Concert> searchAll(Pageable pageable);
    Page<Concert> searchAll(Pageable pageable, List<Concert> exclusions);
    Page<Concert> searchAllOngoingProject(Pageable pageable);
    Page<Concert> searchAllOngoingProject(Pageable pageable, List<Concert> exclusions);
    List<Concert> searchAllFinishedProjectByArtist(Long artistId);
    Concert searchById(Long id);
    Page<Concert> searchAllByLikeKeyword(String keyword, Pageable pageable);
    Page<Concert> searchAllByLikeArtist(String artist, Pageable pageable);
}
