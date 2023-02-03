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
    @Column(name="community_like_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long community_like_id;

    @Column(name="community_like_created")
    @CreationTimestamp
    private LocalDateTime community_like_created = LocalDateTime.now();

    @Column(name="community_like_deleted")
    private boolean community_like_deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="community_id")
    private Community community;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ueser_id")
    private User user;

}
