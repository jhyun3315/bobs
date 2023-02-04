package com.b304.bobs.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="community_comment")
@Getter @Setter
@Builder
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="community_id")
    private Community community;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

}
