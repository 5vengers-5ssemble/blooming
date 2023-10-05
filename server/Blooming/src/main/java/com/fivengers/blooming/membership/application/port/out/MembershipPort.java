package com.fivengers.blooming.membership.application.port.out;

import com.fivengers.blooming.membership.domain.Membership;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MembershipPort {

    Membership save(Membership membership);
    Page<Membership> findLatestSeasons(Pageable pageable);
    Page<Membership> findByBetweenSeasonStartAndSeasonEnd(Pageable pageable, LocalDateTime now);
    Optional<Membership> findByArtistIdAndBetweenSeasonStartAndSeasonEnd(Long artistId,
            LocalDateTime now);
    Optional<Membership> findById(Long membershipId);
    List<Membership> findByTopNSalesCount(long n);
    Page<Membership> findByArtistNameContains(Pageable pageable, String artistName);
    Membership update(Membership membership);
}
