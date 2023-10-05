package com.fivengers.blooming.member.application.port;

import com.fivengers.blooming.config.security.jwt.JwtProvider;
import com.fivengers.blooming.config.security.jwt.JwtToken;
import com.fivengers.blooming.config.security.oauth2.LoginUser;
import com.fivengers.blooming.config.security.oauth2.mapper.LoginUserMapper;
import com.fivengers.blooming.global.exception.auth.JwtNotFoundException;
import com.fivengers.blooming.global.exception.member.MemberTokenNotFoundException;
import com.fivengers.blooming.member.application.port.in.MemberTokenUseCase;
import com.fivengers.blooming.member.application.port.in.dto.MemberTokenRefreshRequest;
import com.fivengers.blooming.member.application.port.out.MemberTokenPort;
import com.fivengers.blooming.member.domain.MemberToken;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberTokenService implements MemberTokenUseCase {

    private final MemberTokenPort memberTokenPort;
    private final LoginUserMapper loginUserMapper;
    private final JwtProvider jwtProvider;


    @Override
    @Transactional
    public JwtToken createJwtToken(LoginUser loginUser) {
        JwtToken jwtToken = jwtProvider.createJwtToken(loginUser);
        Optional<MemberToken> nullableMemberToken =
                memberTokenPort.findByMemberId(loginUser.getMemberId());

        if (nullableMemberToken.isPresent()) {
            MemberToken memberToken = nullableMemberToken.get();
            memberToken.modify(jwtToken.getRefreshToken());
            return jwtToken;
        }

        memberTokenPort.save(MemberToken.builder()
                .refreshToken(jwtToken.getRefreshToken())
                .member(loginUser.getMember())
                .build());
        return jwtToken;
    }

    @Override
    public MemberToken findByMemberId(Long memberId) {
        return memberTokenPort.findByMemberId(memberId)
                .orElseThrow(MemberTokenNotFoundException::new);
    }

    @Override
    public MemberToken findByRefreshToken(String refreshToken) {
        return memberTokenPort.findByRefreshToken(refreshToken)
                .orElseThrow(JwtNotFoundException::new);
    }

    @Override
    @Transactional
    public JwtToken refresh(MemberTokenRefreshRequest request, MemberToken memberToken) {
        JwtToken jwtToken = jwtProvider.createJwtToken(
                loginUserMapper.toLoginUser(memberToken.getMember()));
        memberToken.modify(jwtToken.getRefreshToken());
        memberTokenPort.update(memberToken);

        return jwtToken;
    }
}
