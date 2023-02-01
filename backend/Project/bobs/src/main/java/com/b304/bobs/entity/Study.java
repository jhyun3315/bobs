package com.b304.bobs.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="study")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Study {
    @Id
    @Column(name="study_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int study_id;

    @Column(name="study_title")
    private String study_title;

    @Column(name="study_content")
    private String study_content;

    @Column(name="study_startdate")
    private String study_startdate;

    @Column(name="study_lock")
    private Boolean study_lock;

    @Column(name="study_status")
    private Boolean study_status;

    private String user_id;
}
