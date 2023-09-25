package com.fivengers.blooming.project.adapter.out.persistence.repository;

import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistJpaEntity;
import com.fivengers.blooming.project.adapter.out.persistence.entity.ActivityJpaEntity;
import com.fivengers.blooming.project.adapter.out.persistence.entity.ConcertJpaEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActivitySpringDataRepository extends JpaRepository<ActivityJpaEntity, Long> {

    @Query("select a from ActivityJpaEntity a where a.endedAt > current date ")
    Page<ActivityJpaEntity> findAllOngoingProject(Pageable pageable);

    @Query("select a from ActivityJpaEntity a where a.artist = :artist")
    Page<ActivityJpaEntity> findAllByArtist(@Param("artist") ArtistJpaEntity artist, Pageable pageable);

    @Query("select a from ActivityJpaEntity a where a.artist = :artist and a.endedAt < current date ")
    List<ActivityJpaEntity> findAllFinishedProjectByArtist(@Param("artist") ArtistJpaEntity artist, Pageable pageable);

    @Query("select a from ActivityJpaEntity a where a.name like %:keyword%")
    Page<ActivityJpaEntity> findAllByLikeKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("select a from ActivityJpaEntity a where a.artist.stageName like %:artist%")
    Page<ActivityJpaEntity> findAllByLikeArtist(@Param("artist") String artist, Pageable pageable);
}
