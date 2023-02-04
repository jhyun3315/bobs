package com.b304.bobs.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="study_member")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="study_member_id", nullable = false)
    private Long study_member_id;

    @Column(name="is_study_member",columnDefinition = "BOOLEAN", nullable = false)
    private boolean is_study_member;

    @Column(name="is_meeting_member",columnDefinition = "BOOLEAN",nullable = false)
    private boolean is_meeting_member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="study_id")
    private Study study;

}
