package com.fivengers.blooming.project.application.port.out;


import com.fivengers.blooming.project.domain.Activity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActivityPort {

    Page<Activity> findAll(Pageable pageable);
    Page<Activity> findAllOngoingProject(Pageable pageable);
    List<Activity> findAllFinishedProjectByArtist(Long artistId);
    List<Activity> findBestThreeProject();
    Activity findById(Long id);
    Page<Activity> findAllByLikeKeyword(String query, Pageable pageable);
    Page<Activity> findAllByLikeArtist(String query, Pageable pageable);
    Optional<Activity> findByArtistId(Long artistId);
}
