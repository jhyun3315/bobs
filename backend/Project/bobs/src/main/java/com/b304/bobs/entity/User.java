package com.b304.bobs.entity;

import com.b304.bobs.oauth2.CustomOAuth2User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
@Getter @Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name="user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(name="user_name",columnDefinition = "VARCHAR(20)", nullable = false)
    private String user_name;
    @Column(name="user_profile",columnDefinition = "VARCHAR(100)", nullable = false)
    private String user_profile;
    @Column(name="user_deleted",columnDefinition = "BOOLEAN", nullable = false)
    private Boolean user_deleted;
    @Column(name="user_key",columnDefinition = "VARCHAR(50)", nullable = false)
    private String user_key;

    @Column(name="user_email",columnDefinition = "VARCHAR(30)", nullable = false)
    private String user_email;

    @OneToMany(mappedBy = "user")
    List<Allergy> allergies = new ArrayList<Allergy>();

    @OneToMany(mappedBy = "user")
    List<Community> communities = new ArrayList<Community>();

    @OneToMany(mappedBy = "user")
    List<Study> studies = new ArrayList<Study>();

    @OneToMany(mappedBy = "user")
    List<Refrige> refriges = new ArrayList<Refrige>();

    public void addAllergy(Allergy allergy){
        allergies.add(allergy);
        allergy.setUser(this);
    }

    public void addCommunity(Community community){
        communities.add(community);
        community.setUser(this);
    }

    public void addStudy(Study study){
        studies.add(study);
        study.setUser(this);
    }

    public void addRefrige(Refrige refrige){
        refriges.add(refrige);
        refrige.setUser(this);
    }

    public static User of(CustomOAuth2User oAuth2User) {
        User user = new User();
        user.user_email = oAuth2User.getEmail();
        user.user_name = oAuth2User.getNickname();

        return user;
    }

    public void update(String user_email, String user_name) {
        this.user_email = user_email;
        this.user_name = user_name;
    }

    public void updateRefreshToken(String refreshToken) {
        this.user_key = refreshToken;
    }
}
