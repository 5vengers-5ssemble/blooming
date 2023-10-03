package com.fivengers.blooming.project.adapter.out.persistence.repository;

import com.fivengers.blooming.project.adapter.out.persistence.entity.ActivityJpaEntity;
import com.fivengers.blooming.project.adapter.out.persistence.entity.ConcertJpaEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActivitySpringDataRepository extends JpaRepository<ActivityJpaEntity, Long> {

    Page<ActivityJpaEntity> findByEndedAtIsAfter(Pageable pageable, LocalDateTime now);

    @Query("select a from ActivityJpaEntity a where a.artist.id = :artistId and a.endedAt < current date "
            + "order by a.createdAt desc limit 5")
    List<ActivityJpaEntity> findAllFinishedProjectByArtist(@Param("artistId") Long artistId);

    @Query("select a from ActivityJpaEntity a where a.endedAt > current date order by a.fundingAmount desc limit 3")
    List<ActivityJpaEntity> findBestThreeProject();

    @Query("select a from ActivityJpaEntity a where a.name like %:keyword%")
    Page<ActivityJpaEntity> findAllByLikeKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("select a from ActivityJpaEntity a where a.artist.stageName like %:artist%")
    Page<ActivityJpaEntity> findAllByLikeArtist(@Param("artist") String artist, Pageable pageable);

    @Query("select a from ActivityJpaEntity a where a.artist.id = :artistId and a.endedAt > current date order by a.createdAt desc limit 1")
    Optional<ActivityJpaEntity> findActivityByArtistId(@Param("artistId") Long artistId);
}
