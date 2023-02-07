package com.b304.bobs.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name="ingredient")
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    @Id
    @Column(name="ingredient_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingredient_id;

    @Column(name="ingredient_name",columnDefinition = "VARCHAR(20)", nullable = false)
    private String ingredient_name;

    @Builder
    public Ingredient(Long ingredient_id, String ingredient_name) {
        this.ingredient_id = ingredient_id;
        this.ingredient_name = ingredient_name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="allergy_name")
    private Allergy allergy;

}
