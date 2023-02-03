package com.b304.bobs.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="recipe_ingredient")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeIngredient {
    @Id
    @GeneratedValue
    @Column(name="recipe_ingredient_id",columnDefinition = "INT", nullable = false)
    private int recipe_ingredient_id;

    @Column(name="recipe_ingredient",columnDefinition = "VARCHAR(20)", nullable = false)
    private String recipe_ingredient;

    @Column(name="recipe_ingredient_amount",columnDefinition = "VARCHAR(20)")
    private String recipe_ingredient_amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="recipe")
    private Recipe recipe;
}
