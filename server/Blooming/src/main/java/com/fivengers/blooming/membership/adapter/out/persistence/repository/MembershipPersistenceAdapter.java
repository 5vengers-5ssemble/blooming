package com.fivengers.blooming.membership.adapter.out.persistence.repository;

import com.fivengers.blooming.global.exception.membership.MembershipNotFoundException;
import com.fivengers.blooming.membership.adapter.out.persistence.entity.MembershipJpaEntity;
import com.fivengers.blooming.membership.adapter.out.persistence.mapper.MembershipMapper;
import com.fivengers.blooming.membership.application.port.out.MembershipPort;
import com.fivengers.blooming.membership.domain.Membership;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements MembershipPort {

    private final MembershipQueryRepository membershipQueryRepository;
    private final MembershipSpringDataRepository membershipSpringDataRepository;
    private final MembershipMapper membershipMapper;

    @Override
    @Transactional
    public Membership save(Membership membership) {
        return membershipMapper.toDomain(
                membershipSpringDataRepository.save(membershipMapper.toJpaEntity(membership)));
    }

    @Override
    public Page<Membership> findLatestSeasons(Pageable pageable) {
        Page<MembershipJpaEntity> memberships = membershipQueryRepository.findLatestSeasonsGroupByArtist(
                pageable);

        return new PageImpl<>(memberships.stream()
                .map(membershipMapper::toDomain)
                .toList(), pageable, memberships.getTotalElements());
    }

    @Override
    public Page<Membership> findByBetweenSeasonStartAndSeasonEnd(Pageable pageable,
            LocalDateTime now) {
        Page<MembershipJpaEntity> memberships = membershipQueryRepository
                .findByBetweenSeasonStartAndSeasonEnd(pageable, now);

        return new PageImpl<>(memberships.getContent().stream()
                .map(membershipMapper::toDomain)
                .toList(), pageable, memberships.getTotalElements());
    }

    @Override
    public Optional<Membership> findById(Long membershipId) {
        return membershipQueryRepository.findById(membershipId)
                .map(membershipMapper::toDomain);
    }

    @Override
    public List<Membership> findByTopNSalesCount(long n) {
        return membershipQueryRepository.findTopNSaleCount(n).stream()
                .map(membershipMapper::toDomain)
                .toList();
    }

    @Override
    public Page<Membership> findByArtistNameContains(Pageable pageable, String query) {
        Page<MembershipJpaEntity> byArtistNameLikeQuery = membershipQueryRepository.findByArtistNameLikeQuery(
                pageable, query);

        return PageableExecutionUtils.getPage(byArtistNameLikeQuery.getContent().stream()
                .map(membershipMapper::toDomain)
                .toList(), pageable, byArtistNameLikeQuery::getTotalElements);
    }

    @Override
    public Membership update(Membership membership) {
        MembershipJpaEntity membershipJpaEntity = membershipQueryRepository
                .findById(membership.getId())
                .orElseThrow(MembershipNotFoundException::new);
        membershipJpaEntity.update(membership);
        return membershipMapper.toDomain(membershipJpaEntity);
    }
}
