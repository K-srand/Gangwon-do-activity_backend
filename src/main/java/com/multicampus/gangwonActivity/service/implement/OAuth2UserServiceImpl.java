package com.multicampus.gangwonActivity.service.implement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.multicampus.gangwonActivity.entity.CustomOAuth2User;
import com.multicampus.gangwonActivity.entity.User;
import com.multicampus.gangwonActivity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImpl extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(request);
        String oauthClientName = request.getClientRegistration().getClientName();

        User user = null;
        String userId = null;
        String email = "email@email.com";


        if(oauthClientName.equals("kakao")) {
            userId = "kakao_" + oAuth2User.getAttributes().get("id");
            user = new User(userId, email, "kakao");
        }

        if(!userRepository.existsByUserId(userId)) {
            userRepository.save(user);
        }

        return new CustomOAuth2User(userId);
    }
}
