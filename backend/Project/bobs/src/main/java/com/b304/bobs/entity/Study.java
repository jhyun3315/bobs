package com.b304.bobs.entity;

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

    @Column(name="study_title")
    private String study_title;

    @Column(name="study_content")
    private String study_content;

    @Column(name="study_created")
    @CreationTimestamp
    private LocalDateTime study_created = LocalDateTime.now();

    @Column(name="study_lock")
    private Boolean study_lock;

    @Column(name="study_deleted")
    private Boolean study_deleted;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "study")
    List<StudyMember> study_members = new ArrayList<StudyMember>();

    @OneToMany(mappedBy="study")
    List<StudyComment> study_comments = new ArrayList<StudyComment>();

    @OneToOne
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
