package com.fivengers.blooming.config.security.oauth2.mapper;

import com.fivengers.blooming.config.security.oauth2.OAuth2Request;
import com.fivengers.blooming.member.domain.AuthProvider;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class GoogleAttributeMapper implements AttributeMapper {

    @Override
    public OAuth2Request mapToDto(Map<String, Object> attributes) {
        String accountId = (String) attributes.get("sub");
        String name = (String) attributes.get("name");
        String email = (String) attributes.get("email");
        String imageUrl = (String) attributes.get("picture");
        return new OAuth2Request(accountId, AuthProvider.GOOGLE, name, getAnonymousNickname());
    }

    private static String getAnonymousNickname() {
        return "USER_" + UUID.randomUUID().toString().substring(0, 8);
    }
}
