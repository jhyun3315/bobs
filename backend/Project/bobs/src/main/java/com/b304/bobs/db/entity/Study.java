package com.b304.bobs.db.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="study")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Study {
    @Id
    @Column(name="study_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long study_id;

    @Column(name="study_title",columnDefinition = "VARCHAR(15)", nullable = false)
    private String study_title;

    @Column(name="study_content",columnDefinition = "VARCHAR(150)", nullable = false)
    private String study_content;

    @Column(name="study_time",columnDefinition = "VARCHAR(10)", nullable = false)
    private String study_time;

    @Column(name="study_created",columnDefinition = "DATETIME")
    @CreationTimestamp
    private LocalDateTime study_created = LocalDateTime.now();

    @Column(name="study_lock",columnDefinition = "BOOLEAN", nullable = false)
    private Boolean study_lock;

    @Column(name="study_deleted",columnDefinition = "BOOLEAN", nullable = false)
    private Boolean study_deleted;

    @Builder
    public Study(Long study_id, String study_title, String study_content, String study_time, LocalDateTime study_created, Boolean study_lock, Boolean study_deleted, User user) {
        this.study_id = study_id;
        this.study_title = study_title;
        this.study_content = study_content;
        this.study_time = study_time;
        this.study_created = study_created;
        this.study_lock = study_lock;
        this.study_deleted = study_deleted;
        this.user = user;
    } 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "study")
    List<StudyMember> study_members = new ArrayList<StudyMember>();

    @OneToMany(mappedBy="study")
    List<StudyComment> study_comments = new ArrayList<StudyComment>();


    public void addStudyMember(StudyMember studyMember){
        study_members.add(studyMember);
        studyMember.setStudy(this);
    }

    public void addStudyComment(StudyComment studyComment){
        study_comments.add(studyComment);
        studyComment.setStudy(this);
    }

}
