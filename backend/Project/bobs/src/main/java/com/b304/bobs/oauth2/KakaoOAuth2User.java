package com.b304.bobs.oauth2;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Getter
public class KakaoOAuth2User extends CustomOAuth2User {

    private final Map<String, Object> account;
    private final Map<String, Object> profile;

    @SuppressWarnings("unchecked")
    public KakaoOAuth2User(Map<String, Object> attributes, Collection<? extends GrantedAuthority> authorities, String name) {
        super(attributes, authorities, name);

        this.account =  (Map<String, Object>) attributes.get("kakao_account");
        this.profile = (Map<String, Object>) account.get("profile");
    }

//    public KakaoCustomOAuth2User(Map<String, Object> attributes) {
//        super(attributes);
//        this.account = (Map<String, Object>) attributes.get("kakao_account");
//        this.profile = (Map<String, Object>) account.get("profile");
//    }

    @Override
    public String getEmail() {
        return account.get("email").toString();
    }

    @Override
    public String getNickname() {
        return profile.get("nickname").toString();
    }
}