package com.fivengers.blooming.project.adapter.out.persistence.repository;

import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistJpaEntity;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.project.adapter.out.persistence.entity.ConcertJpaEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConcertSpringDataRepository extends JpaRepository<ConcertJpaEntity, Long> {

    @Query("select c from ConcertJpaEntity c where c not in :exclusions ")
    Page<ConcertJpaEntity> findAll(Pageable pageable, List<ConcertJpaEntity> exclusions);
    @Query("select c from ConcertJpaEntity c where c.endedAt > current date ")
    Page<ConcertJpaEntity> findAllOngoingProject(Pageable pageable);
    @Query("select c from ConcertJpaEntity c where (c.endedAt > current date) and (c not in :exclusions) ")
    Page<ConcertJpaEntity> findAllOngoingProject(Pageable pageable, @Param("exclusions") List<ConcertJpaEntity> exclusions);

    @Query("select c from ConcertJpaEntity c where c.artist.id = :artistId and c.endedAt < current date ")
    List<ConcertJpaEntity> findAllFinishedProjectByArtist(@Param("artistId") Long artistId, Pageable pageable);

    @Query("select c from ConcertJpaEntity c where c.name like %:keyword%")
    Page<ConcertJpaEntity> findAllByLikeKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("select c from ConcertJpaEntity c where c.artist.stageName like %:artist%")
    Page<ConcertJpaEntity> findAllByLikeArtist(@Param("artist") String artist, Pageable pageable);
}
