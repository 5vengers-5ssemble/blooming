package com.fivengers.blooming.config.security.oauth2.mapper;

import com.fivengers.blooming.member.domain.AuthProvider;
import java.util.EnumMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class AttributeMapperFactory {

    private final Map<AuthProvider, AttributeMapper> mapperMap = new EnumMap<>(AuthProvider.class);

    public AttributeMapperFactory(KakaoAttributeMapper kakaoAttributeMapper) {
        mapperMap.put(AuthProvider.KAKAO, kakaoAttributeMapper);
    }

    public AttributeMapper getAttributeMapper(AuthProvider authProvider) {
        return mapperMap.get(authProvider);
    }
}
