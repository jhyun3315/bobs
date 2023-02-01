package com.b304.bobs.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String user_id;

    @Column(name="user_name")
    private String user_name;
    @Column(name="user_profile")
    private String user_profile;
    @Column(name="user_status")
    private Boolean user_status;
}
