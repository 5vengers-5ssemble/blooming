package com.fivengers.blooming.member.application.port;

import com.fivengers.blooming.config.security.oauth2.OAuth2Request;
import com.fivengers.blooming.global.exception.member.InvalidMemberModifyRequestException;
import com.fivengers.blooming.global.exception.member.MemberNotFoundException;
import com.fivengers.blooming.member.application.port.in.MemberUseCase;
import com.fivengers.blooming.member.application.port.in.dto.MemberModifyRequest;
import com.fivengers.blooming.member.application.port.out.MemberPort;
import com.fivengers.blooming.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberUseCase {

    private final MemberPort memberPort;

    public Member searchById(Long memberId) {
        return memberPort.findById(memberId).orElseThrow(MemberNotFoundException::new);
    }

    @Override
    public Member modify(MemberModifyRequest request, Long requestMemberId, Long loginMemberId) {
        if (requestMemberId.equals(loginMemberId)) {
            Member member = memberPort.findById(loginMemberId)
                    .orElseThrow(MemberNotFoundException::new);
            member.modify(request.nickname());

            memberPort.update(member);
            return member;
        }

        throw new InvalidMemberModifyRequestException();
    }

    public Member saveIfNotExists(OAuth2Request oAuth2Request) {
        return memberPort.findByOAuth2Account(oAuth2Request.getAccount())
                .orElseGet(() -> save(oAuth2Request));
    }

    private Member save(OAuth2Request oAuth2Request) {
        return memberPort.save(oAuth2Request.toMember());
    }
}
