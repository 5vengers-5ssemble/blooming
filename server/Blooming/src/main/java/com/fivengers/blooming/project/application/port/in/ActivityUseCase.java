package com.fivengers.blooming.project.application.port.in;

import com.fivengers.blooming.project.domain.Activity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActivityUseCase {

    Page<Activity> searchAll(Pageable pageable);
    Page<Activity> searchAllOngoingProject(Pageable pageable);
    List<Activity> searchAllFinishedProjectByArtist(Long artistId);
    List<Activity> searchBestThreeProject();
    Activity searchById(Long id);
    Page<Activity> searchAllByLikeKeyword(String query, Pageable pageable);
    Page<Activity> searchAllByLikeArtist(String query, Pageable pageable);
    Optional<Activity> searchByArtistId(Long artistId);
}
