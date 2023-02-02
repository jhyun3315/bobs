package com.b304.bobs.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name="ingredient")
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    @Id
    @Column(name="ingredient_id")
    @GeneratedValue
    private Long ingredient_id;

    @Column(name="ingredient_name")
    private String ingredient_name;

    @ManyToOne
    @JoinColumn(name="allergy_name")
    private Allergy allergy;

}
