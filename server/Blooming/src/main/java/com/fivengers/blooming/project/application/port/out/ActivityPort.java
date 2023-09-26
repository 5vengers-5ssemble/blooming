package com.fivengers.blooming.project.application.port.out;


import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.project.domain.Activity;
import com.fivengers.blooming.project.domain.Concert;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActivityPort {

    Page<Activity> findAll(Pageable pageable);
    Page<Activity> findAllOngoingProject(Pageable pageable);
    Page<Activity> findAllByArtist(Artist artist, Pageable pageable);
    List<Activity> findAllFinishedProjectByArtist(Long artistId, Pageable pageable);
    Activity findById(Long id);
    Page<Activity> findAllByLikeKeyword(String query, Pageable pageable);
    Page<Activity> findAllByLikeArtist(String query, Pageable pageable);
}
