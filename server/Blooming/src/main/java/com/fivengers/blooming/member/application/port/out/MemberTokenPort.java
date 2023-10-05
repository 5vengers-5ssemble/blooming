package com.fivengers.blooming.member.application.port.out;

import com.fivengers.blooming.member.domain.MemberToken;
import java.util.Optional;

public interface MemberTokenPort {

    MemberToken save(MemberToken memberToken);
    Optional<MemberToken> findByMemberId(Long memberId);
    MemberToken update(MemberToken memberToken);
    Optional<MemberToken> findByRefreshToken(String refreshToken);
}
