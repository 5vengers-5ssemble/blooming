package com.fivengers.blooming.fixture.membership.adapter.out.persistence;

import com.fivengers.blooming.membership.application.port.out.MembershipPort;
import com.fivengers.blooming.membership.domain.Membership;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.support.PageableExecutionUtils;

public class FakeMembershipPersistenceAdapter implements MembershipPort {

    private Map<Long, Membership> store = new HashMap<>();
    private Long autoIncrementId = 1L;

    public Membership save(Membership membership) {
        if (isPersistenceObject(membership)) {
            store.put(membership.getId(), membership);
            return membership;
        }
        return persist(membership);
    }

    @Override
    public Page<Membership> findLatestSeasons(Pageable pageable) {
        // TODO: 정렬 구현 필요
        Map<Long, Integer> latestSeason = getLatestSeason();
        List<Membership> memberships = store.values().stream()
                .filter(membership -> latestSeason.get(membership.getArtist().getId())
                        .equals(membership.getSeason()))
                .toList();

        return new PageImpl<>(memberships, pageable, store.size());
    }

    @Override
    public Page<Membership> findByBetweenSeasonStartAndSeasonEnd(Pageable pageable, LocalDateTime now) {
        List<Membership> filteredMembership = store.values().stream()
                .filter(membership -> membership.getSeasonStart().isBefore(now)
                        && membership.getSeasonEnd().isAfter(now))
                .toList();

        List<Membership> memberships = filteredMembership.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .toList();

        return new PageImpl<>(memberships, pageable, filteredMembership.size());
    }

    @Override
    public Optional<Membership> findByArtistIdAndBetweenSeasonStartAndSeasonEnd(Long artistId,
            LocalDateTime now) {
        return store.values().stream()
                .filter(membership -> membership.getSeasonStart().isBefore(now)
                        && membership.getSeasonEnd().isAfter(now))
                .filter(membership -> membership.getArtist().getId().equals(artistId))
                .findFirst();
    }

    @Override
    public Optional<Membership> findById(Long membershipId) {
        return store.values().stream()
                .filter(membership -> membership.getId().equals(membershipId))
                .findFirst();
    }

    @Override
    public List<Membership> findByTopNSalesCount(long n) {
        return store.values().stream()
                .sorted(Comparator.comparingInt(Membership::getSaleCount).reversed())
                .limit(n)
                .toList();
    }

    @Override
    public Page<Membership> findByArtistNameContains(Pageable pageable, String artistName) {
        List<Membership> filteringMemberships = store.values().stream()
                .filter(membership -> membership.getArtist().getStageName().contains(artistName))
                .toList();

        List<Membership> memberships = filteringMemberships.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .toList();

        return PageableExecutionUtils.getPage(memberships, pageable, filteringMemberships::size);
    }

    @Override
    public Membership update(Membership membership) {
        return null;
    }

    private Map<Long, Integer> getLatestSeason() {
        Map<Long, Integer> latestSeason = new HashMap<>();
        store.values().forEach(membership ->
                latestSeason.put(membership.getArtist().getId(),
                        Math.max(latestSeason.getOrDefault(membership.getArtist().getId(), 0), membership.getSeason())));
        return latestSeason;
    }

    private static boolean isPersistenceObject(Membership membership) {
        return membership.getId() != null;
    }

    private Membership persist(Membership membership) {
        LocalDateTime now = LocalDateTime.now();
        Membership persistMembership = Membership.builder()
                .id(membership.getId())
                .title(membership.getTitle())
                .description(membership.getDescription())
                .season(membership.getSeason())
                .seasonStart(membership.getSeasonStart())
                .seasonEnd(membership.getSeasonEnd())
                .purchaseStart(membership.getPurchaseStart())
                .purchaseEnd(membership.getPurchaseEnd())
                .saleCount(membership.getSaleCount())
                .thumbnailUrl(membership.getThumbnailUrl())
                .createdAt(now)
                .modifiedAt(now)
                .artist(membership.getArtist())
                .nftSale(membership.getNftSale())
                .build();
        store.put(autoIncrementId, persistMembership);
        autoIncrementId++;
        return persistMembership;
    }
}
