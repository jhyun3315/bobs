package com.b304.bobs.db.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name="study_comment")
@NoArgsConstructor
public class StudyComment {
    @Id
    @Column(name="study_comment_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long study_comment_id;

    @Column(name="study_comment_content",columnDefinition = "VARCHAR(225)", nullable = false)
    private String study_comment_content;

    @Column(name="study_comment_created",columnDefinition = "DATETIME", nullable = false)
    @CreationTimestamp
    private LocalDateTime study_comment_created = LocalDateTime.now();

    @Column(name="study_comment_deleted",columnDefinition = "BOOLEAN", nullable = false)
    private boolean study_comment_deleted;

    @Builder
    public StudyComment(Long study_comment_id, String study_comment_content, LocalDateTime study_comment_created, boolean study_comment_deleted, User user, Study study) {
        this.study_comment_id = study_comment_id;
        this.study_comment_content = study_comment_content;
        this.study_comment_created = study_comment_created;
        this.study_comment_deleted = study_comment_deleted;
        this.user = user;
        this.study = study;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="study_id")
    private Study study;

    public boolean getStudy_comment_deleted() {return this.study_comment_deleted; }
}
