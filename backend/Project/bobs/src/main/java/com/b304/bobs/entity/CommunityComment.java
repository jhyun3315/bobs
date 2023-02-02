package com.b304.bobs.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="community_comment")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommunityComment {
    @Id
    @Column(name="community_comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long community_comment_id;

    @Column(name="community_comment_title")
    private String community_title;

    @Column(name="community_comment_content")
    private String community_comment_content;

    @Column(name="community_comment_created")
    @CreationTimestamp
    private LocalDateTime community_comment_created = LocalDateTime.now();

    @Column(name="community_comment_deleted")
    private boolean community_comment_deleted;

    @ManyToOne
    @JoinColumn(name="community_id")
    private Community community;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

}
