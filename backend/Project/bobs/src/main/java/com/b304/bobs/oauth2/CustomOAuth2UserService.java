package com.b304.bobs.oauth2;

import com.b304.bobs.dto.UserDTO;
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
import java.util.Map;
import java.util.Optional;
import java.util.Set;


@Service
@Slf4j
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    /*
     서드파티 접근을 위한 access token까지 얻은 다음 실행됨
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // accessToken으로 서드파티에 요청해서 사용자 정보를 얻어옴
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // kakao
        log.info("oauth provider: {}", registrationId);

        CustomOAuth2User customOAuth2User = new KakaoOAuth2User(
                oAuth2User.getAttributes(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                "id"
        );

        User user = User.builder()
                .user_deleted(false)
                .user_email(customOAuth2User.getName())
                .user_key(oAuth2User.getName())
                .user_profile(customOAuth2User.getImage())
                .user_name(customOAuth2User.getNickname()).build();

        User result = saveOrUpdate(user);

        System.out.println(customOAuth2User.getName()); // email
        System.out.println(customOAuth2User.getNickname()); // 닉네임
        System.out.println(oAuth2User.getName()); // key
        System.out.println(customOAuth2User.getImage());


//        User user = saveOrUpdate(customOAuth2User);

        log.info("oauth login success - user : {}", result);

//        return new DefaultOAuth2User(
//                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
//                oAuth2User.getAttributes(),
//                "id");
        return customOAuth2User;
    }

    // 이미 계정이 들어있다면 해당 계정을 업데이트 하고 저장, 계정이 없으면 새로 만듦
    private User saveOrUpdate(User user) {
        userRepository.findByEmail(user.getUser_email()).ifPresent(e -> user.setUser_id(e.getUser_id()));
        return userRepository.save(user);
    }
}
