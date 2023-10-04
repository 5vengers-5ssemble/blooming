package com.fivengers.blooming.member.domain;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
public class Member {
    private Long id;
    private AuthProvider oauthProvider;
    private String oauthAccount;
    private String name;
    private String nickname;
    private String account;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<MemberRole> role;

    @Builder
    public Member(Long id,
                  AuthProvider oauthProvider,
                  String oauthAccount,
                  String name,
                  String nickname,
                  String account,
                  LocalDateTime createdAt,
                  LocalDateTime modifiedAt,
                  List<MemberRole> role) {
        this.id = id;
        this.oauthProvider = oauthProvider;
        this.oauthAccount = oauthAccount;
        this.name = name;
        this.nickname = nickname;
        this.account = account;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.role = role;
    }

    public List<SimpleGrantedAuthority> getAuthority() {
        return role.stream()
                .map(MemberRole::name)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    public boolean isArtist() {
        return role.contains(MemberRole.ROLE_ARTIST);
    }
}
