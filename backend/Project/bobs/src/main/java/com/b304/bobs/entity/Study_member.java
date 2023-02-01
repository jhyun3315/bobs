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
public class Study_member {
    @Id
    @Column(name="study_member_status")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private boolean study_member_status;

    @Column(name="meeting_join_status")
    private boolean meeting_join_status;

    private int study_id;

    private String user_id;
}
