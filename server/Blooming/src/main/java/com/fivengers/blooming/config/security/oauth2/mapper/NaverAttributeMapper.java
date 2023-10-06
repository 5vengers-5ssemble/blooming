package com.fivengers.blooming.config.security.oauth2.mapper;

import com.fivengers.blooming.config.security.oauth2.OAuth2Request;
import com.fivengers.blooming.member.domain.AuthProvider;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class NaverAttributeMapper implements AttributeMapper {

    @Override
    public OAuth2Request mapToDto(Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        String account = (String) response.get("id");
        String name = (String) response.get("name");
        String email = (String) response.get("email");
        return new OAuth2Request(account, AuthProvider.NAVER, name, getAnonymousNickname());
    }

    private static String getAnonymousNickname() {
        return "USER_" + UUID.randomUUID().toString().substring(0, 8);
    }
}
