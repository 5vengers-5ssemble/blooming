package com.fivengers.blooming.config.security.oauth2.mapper;

import com.fivengers.blooming.config.security.oauth2.OAuth2Request;
import java.util.Map;

public interface AttributeMapper {

    OAuth2Request mapToDto(Map<String, Object> attributes);
}
