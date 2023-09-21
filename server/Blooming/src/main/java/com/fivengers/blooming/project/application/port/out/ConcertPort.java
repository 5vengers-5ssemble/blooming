package com.fivengers.blooming.project.application.port.out;


import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.project.domain.Concert;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConcertPort {

    Page<Concert> findAll(Pageable pageable);
    Page<Concert> findAllOngoingProject(Pageable pageable);
    Page<Concert> findAllByArtist(Artist artist, Pageable pageable);
    List<Concert> findAllFinishedProjectByArtist(Artist artist, Pageable pageable);
    Concert findById(Long id);
}
