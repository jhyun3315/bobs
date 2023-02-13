package com.b304.bobs.db.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name="study_member")
@Getter @Setter
@NoArgsConstructor
public class StudyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="study_member_id", nullable = false)
    private Long study_member_id;

    @ColumnDefault("false")
    @Column(name="study_member_deleted",columnDefinition = "BOOLEAN", nullable = false)
    private boolean is_study_member;

    @Builder
    public StudyMember(Long study_member_id, boolean is_study_member, boolean is_meeting_member, User user, Study study) {
        this.study_member_id = study_member_id;
        this.is_study_member = is_study_member;
        this.user = user;
        this.study = study;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="study_id")
    private Study study;

}
