package com.fivengers.blooming.config.security.oauth2.mapper;

import com.fivengers.blooming.config.security.oauth2.OAuth2Request;
import com.fivengers.blooming.member.domain.AuthProvider;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class KakaoAttributeMapper implements AttributeMapper {

    @Override
    public OAuth2Request mapToDto(Map<String, Object> attributes) {
        String account = attributes.get("id").toString();
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
        String nickname = (String) profile.get("nickname");
        String email = (String) kakaoAccount.get("memberEmail");
        return new OAuth2Request(account, AuthProvider.KAKAO, nickname, nickname);
    }
}
