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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="recipe_ingredient_id", nullable = false)
    private Long recipe_ingredient_id;

    @Column(name="recipe_ingredient",columnDefinition = "VARCHAR(20)", nullable = false)
    private String recipe_ingredient;

    @Column(name="recipe_ingredient_amount",columnDefinition = "VARCHAR(20)")
    private String recipe_ingredient_amount;

    @Builder
    public RecipeIngredient(Long recipe_ingredient_id, String recipe_ingredient, String recipe_ingredient_amount) {
        this.recipe_ingredient_id = recipe_ingredient_id;
        this.recipe_ingredient = recipe_ingredient;
        this.recipe_ingredient_amount = recipe_ingredient_amount;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="recipe")
    private Recipe recipe;
}
