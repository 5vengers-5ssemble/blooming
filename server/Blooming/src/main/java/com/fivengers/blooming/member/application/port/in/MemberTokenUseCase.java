package com.fivengers.blooming.member.application.port.in;

import com.fivengers.blooming.config.security.jwt.JwtToken;
import com.fivengers.blooming.config.security.oauth2.LoginUser;
import com.fivengers.blooming.member.application.port.in.dto.MemberTokenRefreshRequest;
import com.fivengers.blooming.member.domain.MemberToken;

public interface MemberTokenUseCase {

    JwtToken createJwtToken(LoginUser loginUser);
    MemberToken findByMemberId(Long memberId);
    MemberToken findByRefreshToken(String refreshToken);
    JwtToken refresh(MemberTokenRefreshRequest request, MemberToken memberToken);

}
