package com.fivengers.blooming.config.security.oauth2.mapper;

import com.fivengers.blooming.config.security.oauth2.LoginUser;
import com.fivengers.blooming.member.domain.Member;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class LoginUserMapper {

    public LoginUser toLoginUser(Member member) {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("id", member.getId());
        return new LoginUser(member, attributes, member.getAuthority());
    }
}
