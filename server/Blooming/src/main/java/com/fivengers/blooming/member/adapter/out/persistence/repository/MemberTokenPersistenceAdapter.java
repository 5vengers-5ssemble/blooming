package com.fivengers.blooming.member.adapter.out.persistence.repository;

import com.fivengers.blooming.global.exception.member.MemberTokenNotFoundException;
import com.fivengers.blooming.member.adapter.out.persistence.entity.MemberTokenJpaEntity;
import com.fivengers.blooming.member.adapter.out.persistence.mapper.MemberTokenMapper;
import com.fivengers.blooming.member.application.port.out.MemberTokenPort;
import com.fivengers.blooming.member.domain.MemberToken;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberTokenPersistenceAdapter implements MemberTokenPort {

    private final MemberTokenSpringDataRepository memberTokenSpringDataRepository;
    private final MemberTokenMapper memberTokenMapper;

    @Override
    @Transactional
    public MemberToken save(MemberToken memberToken) {
        return memberTokenMapper.toDomain(memberTokenSpringDataRepository
                .save(memberTokenMapper.toJpaEntity(memberToken)));
    }

    @Override
    public Optional<MemberToken> findByMemberId(Long memberId) {
        return memberTokenSpringDataRepository.findByMemberJpaEntityId(memberId)
                .map(memberTokenMapper::toDomain);
    }

    @Override
    @Transactional
    public MemberToken update(MemberToken memberToken) {
        MemberTokenJpaEntity memberTokenJpaEntity = memberTokenSpringDataRepository
                .findById(memberToken.getId())
                .orElseThrow(MemberTokenNotFoundException::new);
        memberTokenJpaEntity.update(memberToken);

        return memberTokenMapper.toDomain(memberTokenJpaEntity);
    }

    @Override
    public Optional<MemberToken> findByRefreshToken(String refreshToken) {
        return memberTokenSpringDataRepository.findByRefreshToken(refreshToken)
                .map(memberTokenMapper::toDomain);
    }
}
