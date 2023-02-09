package com.b304.bobs.db.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="community_comment")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommunityComment {
    @Id
    @Column(name="community_comment_id",  nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long community_comment_id;

    @Column(name="community_comment_content",columnDefinition = "VARCHAR(225)", nullable = false)
    private String community_comment_content;

    @Column(name="community_comment_created",columnDefinition = "DATETIME" ,nullable = false)
    @CreationTimestamp
    private LocalDateTime community_comment_created = LocalDateTime.now();

    @Column(name="community_comment_deleted",columnDefinition = "BOOLEAN", nullable = false)
    private boolean community_comment_deleted;

    @Builder
    public CommunityComment(Long community_comment_id, String community_comment_content, LocalDateTime community_comment_created, boolean community_comment_deleted) {
        this.community_comment_id = community_comment_id;
        this.community_comment_content = community_comment_content;
        this.community_comment_created = community_comment_created;
        this.community_comment_deleted = community_comment_deleted;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="community_id")
    private Community community;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    public boolean getCommunity_comment_deleted() {
        return this.community_comment_deleted;
    }
}
