package com.b304.bobs.oauth2;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private Map<String, Object> attributes;
    private Collection<? extends GrantedAuthority> authorities;
    private String name;
    private String email;
    private String nickname;

    private String image;

    public CustomOAuth2User(Map<String, Object> attributes, Collection<? extends GrantedAuthority> authorities, String name) {
        this.attributes = attributes;
        this.authorities = authorities;
        this.name = name;
    }

    public CustomOAuth2User(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getImage() { return image; }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return getEmail();
    }
}
