package com.b304.bobs.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="allergy")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Allergy {
    @Id
    @Column(name="allergy_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int allergy_id;

    @Column(name = "allergy_status")
    private boolean allergy_status;

    @Column(name="ingredient_id")
    private int ingredient_id;

    private String user_id;
}
