package com.b304.bobs.entity;

import com.b304.bobs.oauth2.CustomOAuth2User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
@Getter @Setter
@Builder
@NoArgsConstructor
public class User {
    @Id
    @Column(name="user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(name="user_name",columnDefinition = "VARCHAR(20)", nullable = false)
    private String user_name;
    @Column(name="user_profile",columnDefinition = "VARCHAR(100)")
    private String user_profile;

    @Column(name="user_deleted",columnDefinition = "BOOLEAN", nullable = false)
    private Boolean user_deleted;
    @Column(name="user_key",columnDefinition = "VARCHAR(300)", nullable = false)

    private String user_key;

    @Column(name="user_email",columnDefinition = "VARCHAR(30)", nullable = false)
    private String user_email;


    @Builder
    public User(Long user_id, String user_name, String user_profile, Boolean user_deleted, String user_key, String user_email) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_profile = user_profile;
        this.user_deleted = user_deleted;
        this.user_key = user_key;
        this.user_email = user_email;
    }


    public void update(String user_email, String user_name) {
        this.user_email = user_email;
        this.user_name = user_name;
    }

    public void updateRefreshToken(String refreshToken) {
        this.user_key = refreshToken;
    }
}
