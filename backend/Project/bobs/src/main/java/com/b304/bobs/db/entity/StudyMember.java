package com.b304.bobs.db.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name="study_member")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="study_member_id", nullable = false)
    private Long study_member_id;

    @ColumnDefault("false")
    @Column(name="study_member_deleted",columnDefinition = "BOOLEAN", nullable = false)
    private Boolean study_member_deleted;

    @ColumnDefault("false")
    @Column(name="study_member_role",columnDefinition = "BOOLEAN", nullable = false)
    private boolean study_member_role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="study_id")
    private Study study;

}
