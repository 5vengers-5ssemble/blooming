package com.fivengers.blooming.config.security.oauth2;

import com.fivengers.blooming.config.security.oauth2.mapper.AttributeMapperFactory;
import com.fivengers.blooming.config.security.oauth2.mapper.LoginUserMapper;
import com.fivengers.blooming.member.application.port.MemberService;
import com.fivengers.blooming.member.domain.AuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultUserService extends DefaultOAuth2UserService {

    private final MemberService memberService;
    private final AttributeMapperFactory attributeMapperFactory;
    private final LoginUserMapper loginUserMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        AuthProvider authProvider = AuthProvider.valueOf(
                userRequest.getClientRegistration().getClientName().toUpperCase());
        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2Request oAuth2Request = attributeMapperFactory.getAttributeMapper(authProvider)
                .mapToDto(oAuth2User.getAttributes());

        return loginUserMapper.toLoginUser(memberService.saveIfNotExists(oAuth2Request));
    }
}
