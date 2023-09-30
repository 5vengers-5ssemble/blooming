package com.fivengers.blooming.member.application.port;

import com.fivengers.blooming.config.security.oauth2.OAuth2Request;
import com.fivengers.blooming.global.exception.member.MemberNotFoundException;
import com.fivengers.blooming.member.application.port.in.MemberUseCase;
import com.fivengers.blooming.member.application.port.out.MemberPort;
import com.fivengers.blooming.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberUseCase {

    private final MemberPort memberPort;

    public Member findById(Long memberId) {
        return memberPort.findById(memberId).orElseThrow(MemberNotFoundException::new);
    }

    public Member saveIfNotExists(OAuth2Request oAuth2Request) {
        return memberPort.findByOAuth2Account(oAuth2Request.getAccount())
                .orElseGet(() -> save(oAuth2Request));
    }

    private Member save(OAuth2Request oAuth2Request) {
        return memberPort.save(oAuth2Request.toMember());
    }
}
