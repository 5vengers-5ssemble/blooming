package com.fivengers.blooming.project.adapter.out.persistence.repository;


import com.fivengers.blooming.artist.adapter.out.persistence.mapper.ArtistMapper;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.global.exception.project.ProjectNotFoundException;
import com.fivengers.blooming.project.adapter.out.persistence.entity.ActivityJpaEntity;
import com.fivengers.blooming.project.adapter.out.persistence.entity.ConcertJpaEntity;
import com.fivengers.blooming.project.adapter.out.persistence.mapper.ActivityMapper;
import com.fivengers.blooming.project.application.port.out.ActivityPort;
import com.fivengers.blooming.project.domain.Activity;
import com.fivengers.blooming.project.domain.Concert;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ActivityPersistenceAdapter implements ActivityPort {

    private final ActivityMapper activityMapper;
    private final ArtistMapper artistMapper;
    private final ActivitySpringDataRepository activitySpringDataRepository;

    @Override
    public Page<Activity> findAll(Pageable pageable) {
        Page<ActivityJpaEntity> concerts = activitySpringDataRepository.findAll(pageable);
        return new PageImpl<>(concerts.stream()
                .map(activityMapper::toDomain)
                .toList(), pageable, concerts.getTotalElements());
    }

    @Override
    public Page<Activity> findAllOngoingProject(Pageable pageable) {
        Page<ActivityJpaEntity> concerts = activitySpringDataRepository
                .findAllOngoingProject(pageable);
        return new PageImpl<>(concerts.stream()
                .map(activityMapper::toDomain)
                .toList(), pageable, concerts.getTotalElements());
    }

    @Override
    public Page<Activity> findAllByArtist(Artist artist, Pageable pageable) {
        Page<ActivityJpaEntity> concerts = activitySpringDataRepository.findAllByArtist(
                artistMapper.toJpaEntity(artist), pageable);
        return new PageImpl<>(concerts.stream()
                .map(activityMapper::toDomain)
                .toList(), pageable, concerts.getTotalElements());
    }

    @Override
    public List<Activity> findAllFinishedProjectByArtist(Long artistId, Pageable pageable) {
        return activitySpringDataRepository.findAllFinishedProjectByArtist(artistId, pageable)
                .stream()
                .map(activityMapper::toDomain)
                .toList();
    }

    @Override
    public Activity findById(Long id) {
        return activitySpringDataRepository.findById(id)
                .map(activityMapper::toDomain)
                .orElseThrow(ProjectNotFoundException::new);
    }

    @Override
    public Page<Activity> findAllByLikeKeyword(String query, Pageable pageable) {
        Page<ActivityJpaEntity> activities = activitySpringDataRepository.findAllByLikeKeyword(
                query, pageable);
        return new PageImpl<>(activities.stream()
                .map(activityMapper::toDomain)
                .toList(), pageable, activities.getTotalElements());
    }

    @Override
    public Page<Activity> findAllByLikeArtist(String query, Pageable pageable) {
        Page<ActivityJpaEntity> activities = activitySpringDataRepository.findAllByLikeArtist(
                query, pageable);
        return new PageImpl<>(activities.stream()
                .map(activityMapper::toDomain)
                .toList(), pageable, activities.getTotalElements());
    }
}
