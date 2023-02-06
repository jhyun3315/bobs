package com.b304.bobs.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="community")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Community {
    @Id
    @Column(name="community_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long community_id;

    @Column(name="community_title",columnDefinition = "VARCHAR(15)", nullable = false)
    private String community_title;

    @Column(name="community_content",columnDefinition = "VARCHAR(225)", nullable = false)
    private String community_content;

    @Column(name="community_img",columnDefinition = "VARCHAR(100)")
    private String community_img;

    @Column(name="community_created",columnDefinition = "DATETIME", nullable = false)
    @CreationTimestamp
    private LocalDateTime community_createdTime = LocalDateTime.now();

    @Column(name="community_deleted",columnDefinition = "BOOLEAN", nullable = false)
    private boolean community_deleted;

    public boolean getCommunity_deleted(){
        return this.community_deleted;
    }

    @Column(name="community_hit", columnDefinition = "INT", nullable = false)
    private int community_hit;

    @Builder
    public Community(Long community_id, String community_title, String community_content, String community_img, LocalDateTime community_createdTime, boolean community_deleted, int community_hit) {
        this.community_id = community_id;
        this.community_title = community_title;
        this.community_content = community_content;
        this.community_img = community_img;
        this.community_createdTime = community_createdTime;
        this.community_deleted = community_deleted;
        this.community_hit = community_hit;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "community",fetch = FetchType.LAZY)
    List<CommunityComment> community_comments = new ArrayList<CommunityComment>();

    @OneToMany(mappedBy = "community",fetch = FetchType.LAZY)
    List<CommunityLike> community_likes = new ArrayList<CommunityLike>();
}
