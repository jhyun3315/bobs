package com.b304.bobs.db.entity;


import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="community_like")
@Getter @Setter
@NoArgsConstructor
public class CommunityLike {
    @Id
    @Column(name="community_like_id", nullable =  false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long community_like_id;

    @Column(name="community_like_created",columnDefinition = "DATETIME", nullable = false)
    @CreationTimestamp
    private LocalDateTime community_like_created = LocalDateTime.now();

    @ColumnDefault("false")
    @Column(name="community_like_deleted",columnDefinition = "BOOLEAN", nullable = false)
    private boolean community_like_deleted;

    @Builder
    public CommunityLike(Long community_like_id, LocalDateTime community_like_created, boolean community_like_deleted, Community community, User user) {
        this.community_like_id = community_like_id;
        this.community_like_created = community_like_created;
        this.community_like_deleted = community_like_deleted;
        this.community = community;
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="community_id")
    private Community community;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

}
