package com.b304.bobs.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="meeting")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Meeting {
    @Id
    @Column(name="meeting_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int meeting_id;

    @Column(name="meeting_session_name")
    private String meeting_session_name;

    @Column(name="meeting_url")
    private String meeting_url;

    @Column(name="meeting_lock")
    private boolean meeting_lock;

    @Column(name="meeting_status")
    private boolean meeting_status;

    @Column(name="meeting_time")
    private String meeting_time;

    private int study_id;

}
