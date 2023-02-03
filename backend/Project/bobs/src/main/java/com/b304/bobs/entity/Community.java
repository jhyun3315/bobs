package com.b304.bobs.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="community")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Community {
    @Id
    @Column(name="community_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long community_id;

    @Column(name="community_title")
    private String community_title;

    @Column(name="community_content")
    private String community_content;

    @Column(name="community_img")
    private String community_img;

    @Column(name="community_created")
    @CreationTimestamp
    private LocalDateTime community_createdTime = LocalDateTime.now();

    @Column(name="community_deleted")
    private boolean community_deleted;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "community")
    List<CommunityComment> community_comments = new ArrayList<CommunityComment>();

    @OneToMany
    List<CommunityLike> communityLikes = new ArrayList<CommunityLike>();
}
