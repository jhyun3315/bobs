package com.b304.bobs.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name="study_comment")
@AllArgsConstructor
@NoArgsConstructor
public class StudyComment {
    @Id
    @Column(name="study_comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long study_comment_id;

    @Column(name="study_comment_content")
    private String study_comment_content;

    @Column(name="study_comment_created")
    @CreationTimestamp
    private LocalDateTime study_comment_created = LocalDateTime.now();

    @Column(name="study_comment_deleted")
    private boolean study_comment_deleted;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="study_id")
    private Study study;

}
