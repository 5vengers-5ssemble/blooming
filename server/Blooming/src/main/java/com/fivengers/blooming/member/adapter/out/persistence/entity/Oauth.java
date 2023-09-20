package com.fivengers.blooming.member.adapter.out.persistence.entity;

import com.fivengers.blooming.member.domain.AuthProvider;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Oauth {

    @Enumerated(EnumType.STRING)
    private AuthProvider oauthProvider;

    @Column(nullable = false)
    private String oauthAccount;

    public Oauth(AuthProvider oauthProvider, String oauthAccount) {
        this.oauthProvider = oauthProvider;
        this.oauthAccount = oauthAccount;
    }
}
