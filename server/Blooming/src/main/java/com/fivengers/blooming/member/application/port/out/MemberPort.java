package com.fivengers.blooming.member.application.port.out;

import com.fivengers.blooming.config.security.oauth2.OAuth2Request;
import com.fivengers.blooming.member.domain.Member;
import java.util.Optional;

public interface MemberPort {

    Member save(Member member);
    Optional<Member> findById(Long memberId);
    Optional<Member> findByOAuth2Account(String account);
    Member update(Member member);
}
