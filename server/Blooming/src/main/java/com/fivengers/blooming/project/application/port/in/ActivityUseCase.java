package com.fivengers.blooming.project.application.port.in;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.project.domain.Activity;
import com.fivengers.blooming.project.domain.Concert;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActivityUseCase {

    Page<Activity> searchAll(Pageable pageable);
    Page<Activity> searchAllOngoingProject(Pageable pageable);
    Page<Activity> searchAllByArtist(Artist artist, Pageable pageable);
    List<Activity> searchAllFinishedProjectByArtist(Artist artist);
    Activity searchById(Long id);
}
