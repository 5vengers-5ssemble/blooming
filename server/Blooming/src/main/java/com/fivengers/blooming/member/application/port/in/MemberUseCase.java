package com.fivengers.blooming.member.application.port.in;

import com.fivengers.blooming.member.domain.Member;

public interface MemberUseCase {

    Member findById(Long memberId);
}
