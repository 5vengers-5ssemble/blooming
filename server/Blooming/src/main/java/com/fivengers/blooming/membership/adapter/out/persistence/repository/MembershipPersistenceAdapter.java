package com.fivengers.blooming.membership.adapter.out.persistence.repository;

import com.fivengers.blooming.membership.adapter.out.persistence.entity.MembershipJpaEntity;
import com.fivengers.blooming.membership.adapter.out.persistence.mapper.MembershipMapper;
import com.fivengers.blooming.membership.application.port.out.MembershipPort;
import com.fivengers.blooming.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements MembershipPort {

    private final MembershipQueryRepository membershipQueryRepository;
    private final MembershipMapper membershipMapper;

    @Override
    public Page<Membership> findLatestSeasons(Pageable pageable) {
        Page<MembershipJpaEntity> memberships = membershipQueryRepository.findLatestSeasonsGroupByArtist(pageable);

        return new PageImpl<>(memberships.stream()
                .map(membershipMapper::toDomain)
                .toList(), pageable, memberships.getTotalElements());
    }
}
