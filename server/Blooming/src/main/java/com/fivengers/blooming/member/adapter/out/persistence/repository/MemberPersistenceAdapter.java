package com.fivengers.blooming.member.adapter.out.persistence.repository;

import com.fivengers.blooming.global.exception.member.MemberNotFoundException;
import com.fivengers.blooming.member.adapter.out.persistence.entity.MemberJpaEntity;
import com.fivengers.blooming.member.adapter.out.persistence.mapper.MemberMapper;
import com.fivengers.blooming.member.application.port.out.MemberPort;
import com.fivengers.blooming.member.domain.Member;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements MemberPort {

    private final MemberMapper memberMapper;
    private final MemberSpringDataRepository memberSpringDataRepository;
    private final MemberQueryRepository memberQueryRepository;

    @Override
    @Transactional
    public Member save(Member member) {
        return memberMapper.toDomain(memberSpringDataRepository
                .save(memberMapper.toJpaEntity(member)));
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return memberQueryRepository.findById(memberId)
                .map(memberMapper::toDomain);
    }

    @Override
    public Optional<Member> findByOAuth2Account(String account) {
        return memberQueryRepository.findByOAuth2Account(account)
                .map(memberMapper::toDomain);
    }

    @Override
    @Transactional
    public Member update(Member member) {
        MemberJpaEntity memberJpaEntity = memberSpringDataRepository.findById(member.getId())
                .orElseThrow(MemberNotFoundException::new);

        memberJpaEntity.update(member);
        return memberMapper.toDomain(memberJpaEntity);
    }
}
