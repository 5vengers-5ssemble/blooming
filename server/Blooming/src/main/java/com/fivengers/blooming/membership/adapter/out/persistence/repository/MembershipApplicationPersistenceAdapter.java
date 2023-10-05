package com.fivengers.blooming.membership.adapter.out.persistence.repository;

import com.fivengers.blooming.global.exception.membership.MembershipApplicationNotFoundException;
import com.fivengers.blooming.membership.adapter.out.persistence.entity.MembershipApplicationJpaEntity;
import com.fivengers.blooming.membership.adapter.out.persistence.mapper.MembershipApplicationMapper;
import com.fivengers.blooming.membership.application.port.out.MembershipApplicationPort;
import com.fivengers.blooming.membership.domain.MembershipApplication;
import com.fivengers.blooming.membership.domain.MembershipApplicationState;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MembershipApplicationPersistenceAdapter implements MembershipApplicationPort {

    private final MembershipApplicationSpringDataRepository springDataRepository;
    private final MembershipApplicationQueryRepository queryRepository;
    private final MembershipApplicationMapper mapper;

    @Override
    public Page<MembershipApplication> findByApplicationState(Pageable pageable,
            MembershipApplicationState applicationState) {
        Page<MembershipApplicationJpaEntity> applications = queryRepository.findByApplicationState(
                pageable, applicationState);
        return new PageImpl<>(applications.getContent().stream()
                .map(mapper::toDomain)
                .toList(), pageable, applications.getTotalElements());
    }

    @Override
    @Transactional
    public MembershipApplication save(MembershipApplication membershipApplication) {
        return mapper.toDomain(
                springDataRepository.save(mapper.toJpaEntity(membershipApplication)));
    }

    @Override
    public Optional<MembershipApplication> findById(Long applicationId) {
        return queryRepository.findById(applicationId)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<MembershipApplication> findByMemberIdAndApplicationState(Long memberId,
            MembershipApplicationState applicationState) {
        return queryRepository.findByMemberIdAndApplicationState(memberId, applicationState)
                .map(mapper::toDomain);
    }

    @Override
    @Transactional
    public MembershipApplication update(MembershipApplication membershipApplication) {
        MembershipApplicationJpaEntity application = queryRepository
                .findById(membershipApplication.getId())
                .orElseThrow(MembershipApplicationNotFoundException::new);

        application.update(membershipApplication);
        return mapper.toDomain(application);
    }
}
