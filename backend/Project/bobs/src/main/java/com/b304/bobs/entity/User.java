package com.b304.bobs.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
@Getter @Setter
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

}
