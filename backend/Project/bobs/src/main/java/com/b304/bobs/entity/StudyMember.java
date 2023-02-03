package com.b304.bobs.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="studymember")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="study_member_id")
    private Long study_member_id;

    @Column(name="is_study_member")
    private boolean is_study_member;

    @Column(name="is_meeting_member")
    private boolean is_meeting_member;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="study_id")
    private Study study;

}
