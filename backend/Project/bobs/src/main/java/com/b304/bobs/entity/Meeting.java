package com.b304.bobs.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name="meeting")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Meeting {
    @Id
    @Column(name="meeting_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meeting_id;

    @Column(name="meeting_url",columnDefinition = "VARCHAR(225)",length = 225)
    private String meeting_url;

    @Column(name="meeting_lock",columnDefinition = "BOOLEAN", nullable = false)
    private boolean meeting_lock;

    @Column(name="meeting_deleted",columnDefinition = "BOOLEAN", nullable = false)
    private boolean meeting_deleted;

    @Column(name="meeting_created",columnDefinition = "DATETIME", nullable = false)
    @CreationTimestamp
    private LocalDateTime meeting_created  = LocalDateTime.now();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="study_id")
    private Study study;

}
