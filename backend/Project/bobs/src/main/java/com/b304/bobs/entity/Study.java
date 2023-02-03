package com.b304.bobs.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="study")
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Study {
    @Id
    @Column(name="study_id",columnDefinition = "INT", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int study_id;

    @Column(name="study_title",columnDefinition = "VARCHAR(15)", nullable = false)
    private String study_title;

    @Column(name="study_content",columnDefinition = "VARCHAR(150)", nullable = false)
    private String study_content;

    @Column(name="study_time",columnDefinition = "VARCHAR(10)", nullable = false)
    private String study_time;

    @Column(name="study_created",columnDefinition = "DATETIME", nullable = false)
    @CreationTimestamp
    private LocalDateTime study_created = LocalDateTime.now();

    @Column(name="study_lock",columnDefinition = "BOOLEAN", nullable = false)
    private Boolean study_lock;

    @Column(name="study_deleted",columnDefinition = "BOOLEAN", nullable = false)
    private Boolean study_deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "study")
    List<StudyMember> study_members = new ArrayList<StudyMember>();

    @OneToMany(mappedBy="study")
    List<StudyComment> study_comments = new ArrayList<StudyComment>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="meeting_id")
    private Meeting meeting;

    public void addStudyMember(StudyMember studyMember){
        study_members.add(studyMember);
        studyMember.setStudy(this);
    }

    public void addStudyComment(StudyComment studyComment){
        study_comments.add(studyComment);
        studyComment.setStudy(this);
    }

}
