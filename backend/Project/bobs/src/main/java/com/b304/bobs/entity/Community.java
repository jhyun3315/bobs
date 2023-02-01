package com.b304.bobs.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="community")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Community {
    @Id
    @Column(name="community_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int community_id;

    @Column(name="community_title")
    private String community_title;

    @Column(name="community_content")
    private String community_content;

    @Column(name="community_img")
    private String community_img;

    @Column(name="community_createtime")
    private String community_createtime;

    @Column(name="community_status")
    private boolean community_status;

    private String user_id;
}
