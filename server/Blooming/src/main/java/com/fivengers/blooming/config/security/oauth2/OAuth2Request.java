package com.fivengers.blooming.config.security.oauth2;

import com.fivengers.blooming.member.domain.AuthProvider;
import com.fivengers.blooming.member.domain.Member;
import com.fivengers.blooming.member.domain.MemberRole;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuth2Request {

    private String account;
    private AuthProvider authProvider;
    private String name;
    private String nickname;

    @Builder
    public OAuth2Request(String account, AuthProvider authProvider, String name, String nickname) {
        this.account = account;
        this.authProvider = authProvider;
        this.name = name;
        this.nickname = nickname;
    }

    public Member toMember() {
        return Member.builder()
                .oauthProvider(authProvider)
                .oauthAccount(account)
                .name(name)
                .nickname(nickname)
                .role(List.of(MemberRole.ROLE_USER))
                .build();
    }
}
