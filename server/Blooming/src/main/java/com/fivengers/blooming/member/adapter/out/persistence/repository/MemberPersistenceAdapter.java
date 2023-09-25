package com.fivengers.blooming.member.adapter.out.persistence.repository;

import com.fivengers.blooming.global.exception.member.MemberNotFoundException;
import com.fivengers.blooming.member.adapter.out.persistence.mapper.MemberMapper;
import com.fivengers.blooming.member.application.port.out.MemberPort;
import com.fivengers.blooming.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements MemberPort {

    private final MemberMapper memberMapper;
    private final MemberSpringDataRepository memberSpringDataRepository;

    @Override
    public Member findById(Long memberId) {
        return memberMapper.toDomain(memberSpringDataRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new));
    }
}
