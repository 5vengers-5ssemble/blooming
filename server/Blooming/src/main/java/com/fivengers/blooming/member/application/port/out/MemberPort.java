package com.fivengers.blooming.member.application.port.out;

import com.fivengers.blooming.member.domain.Member;

public interface MemberPort {

    Member findById(Long memberId);
}
