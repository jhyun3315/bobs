package com.b304.bobs.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name="study_comment")
@Builder
@AllArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="study_id")
    private Study study;

}
