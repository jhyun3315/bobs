package com.b304.bobs.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="community_like")
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommunityLike {
    @Id
    @Column(name="community_like_id",columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int community_like_id;

    @Column(name="community_like_created",columnDefinition = "DATETIME", nullable = false)
    @CreationTimestamp
    private LocalDateTime community_like_created = LocalDateTime.now();

    @Column(name="community_like_deleted",columnDefinition = "BOOLEAN", nullable = false)
    private boolean community_like_deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="community_id")
    private Community community;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

}
