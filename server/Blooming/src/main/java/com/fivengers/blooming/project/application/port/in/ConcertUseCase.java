package com.fivengers.blooming.project.application.port.in;

import com.fivengers.blooming.project.domain.Concert;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConcertUseCase {

    Page<Concert> searchAll(Pageable pageable);
    Page<Concert> searchAllOngoingProject(Pageable pageable);
    List<Concert> searchAllFinishedProjectByArtist(Long artistId);
    List<Concert> searchBestThreeProject();
    Concert searchById(Long id);
    Page<Concert> searchAllByLikeKeyword(String keyword, Pageable pageable);
    Page<Concert> searchAllByLikeArtist(String artist, Pageable pageable);
    Optional<Concert> searchByArtistId(Long artistId);
}
