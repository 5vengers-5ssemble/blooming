package com.fivengers.blooming.project.application.service;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.project.application.port.in.ActivityUseCase;
import com.fivengers.blooming.project.application.port.out.ActivityPort;
import com.fivengers.blooming.project.domain.Activity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityService implements ActivityUseCase {

    private final ActivityPort activityPort;

    @Override
    public Page<Activity> searchAll(Pageable pageable) {
        return activityPort.findAll(pageable);
    }

    @Override
    public Page<Activity> searchAllOngoingProject(Pageable pageable) {
        return activityPort.findAllOngoingProject(pageable);
    }

    @Override
    public Page<Activity> searchAllByArtist(Artist artist, Pageable pageable) {
        return activityPort.findAllByArtist(artist, pageable);
    }

    @Override
    public List<Activity> searchAllFinishedProjectByArtist(Long artistId) {
        return activityPort.findAllFinishedProjectByArtist(artistId,
                PageRequest.of(0, 5, Sort.by("createdAt")
                        .descending()));
    }

    @Override
    public Activity searchById(Long id) {
        return activityPort.findById(id);
    }

    @Override
    public Page<Activity> searchAllByLikeKeyword(String query, Pageable pageable) {
        return activityPort.findAllByLikeKeyword(query, pageable);
    }

    @Override
    public Page<Activity> searchAllByLikeArtist(String query, Pageable pageable) {
        return activityPort.findAllByLikeArtist(query, pageable);
    }
}
