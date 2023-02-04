package com.b304.bobs.oauth2;

import com.b304.bobs.entity.User;
import com.b304.bobs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        log.info("oauth provider: {}", registrationId);

        CustomOAuth2User customOAuth2User = new KakaoOAuth2User(
                oAuth2User.getAttributes(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                "id"
        );


        User user = saveOrUpdate(customOAuth2User);
        log.info("oauth login success - user : {}", user);

//        return new DefaultOAuth2User(
//                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
//                oAuth2User.getAttributes(),
//                "id");
        return customOAuth2User;
    }

    private User saveOrUpdate(CustomOAuth2User oAuth2User) {
        User user = User.of(oAuth2User);
        userRepository.findByEmail(user.getUser_email()).ifPresent(entity -> user.setUser_id(entity.getUser_id()));
        return userRepository.save(user);
    }
}
