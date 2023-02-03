package com.b304.bobs.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name="ingredient")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    @Id
    @Column(name="ingredient_id")
    @GeneratedValue
    private Long ingredient_id;

    @Column(name="ingredient_name")
    private String ingredient_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="allergy_name")
    private Allergy allergy;

}
