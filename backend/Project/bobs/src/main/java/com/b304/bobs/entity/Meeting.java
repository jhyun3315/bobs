package com.b304.bobs.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name="meeting")
@Getter @Setter
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

<<<<<<< HEAD
    @Column(name="meeting_deleted")
=======
    @Column(name="meeting_deleted",columnDefinition = "BOOLEAN", nullable = false)
>>>>>>> develop
    private boolean meeting_deleted;

    @Column(name="meeting_created",columnDefinition = "DATETIME", nullable = false)
    @CreationTimestamp
    private LocalDateTime meeting_created  = LocalDateTime.now();

    @Builder
    public Meeting(Long meeting_id, String meeting_url, boolean meeting_lock, boolean meeting_deleted, LocalDateTime meeting_created) {
        this.meeting_id = meeting_id;
        this.meeting_url = meeting_url;
        this.meeting_lock = meeting_lock;
        this.meeting_deleted = meeting_deleted;
        this.meeting_created = meeting_created;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="study_id")
    private Study study;

}
