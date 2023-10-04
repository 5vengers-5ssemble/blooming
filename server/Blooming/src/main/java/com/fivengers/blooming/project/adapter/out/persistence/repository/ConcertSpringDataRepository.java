package com.fivengers.blooming.project.adapter.out.persistence.repository;

import com.fivengers.blooming.project.adapter.out.persistence.entity.ConcertJpaEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConcertSpringDataRepository extends JpaRepository<ConcertJpaEntity, Long> {

    Page<ConcertJpaEntity> findByEndedAtIsAfter(Pageable pageable, LocalDateTime now);

    @Query("select c from ConcertJpaEntity c where c.artist.id = :artistId and c.endedAt < current date "
            + "order by c.createdAt desc limit 5")
    List<ConcertJpaEntity> findAllFinishedProjectByArtist(@Param("artistId") Long artistId);

    @Query("select c from ConcertJpaEntity c where c.endedAt > current date order by c.fundingAmount desc limit 3")
    List<ConcertJpaEntity> findBestThreeProject();

    @Query("select c from ConcertJpaEntity c where c.name like %:keyword%")
    Page<ConcertJpaEntity> findAllByLikeKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("select c from ConcertJpaEntity c where c.artist.stageName like %:artist%")
    Page<ConcertJpaEntity> findAllByLikeArtist(@Param("artist") String artist, Pageable pageable);

    @Query("select c from ConcertJpaEntity c where c.artist.id = :artistId and c.endedAt > current date order by c.createdAt desc limit 1")
    Optional<ConcertJpaEntity> findConcertByArtistId(@Param("artistId") Long artistId);
}
