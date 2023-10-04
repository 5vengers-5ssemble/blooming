package com.fivengers.blooming.config.security.oauth2;

import com.fivengers.blooming.member.domain.Member;
import java.util.Collection;
import java.util.Map;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
public class LoginUser implements OAuth2User {

    private final Member member;
    private final Map<String, Object> attributes;
    private final Collection<? extends GrantedAuthority> authorities;

    public LoginUser(Member member, Map<String, Object> attributes,
            Collection<? extends GrantedAuthority> authorities) {
        this.member = member;
        this.attributes = attributes;
        this.authorities = authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return member.getId().toString();
    }

    public Long getMemberId() {
        return member.getId();
    }
}
