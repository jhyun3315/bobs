package com.b304.bobs.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="recipe_ingredient")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeIngredient {
    @Id
    @GeneratedValue
    @Column(name="recipe_ingredient_id")
    private Long recipe_ingredient_id;

    @Column(name="recipe_ingredient")
    private String recipe_ingredient;

    @Column(name="recipe_ingredient_amount")
    private String recipe_ingredient_amount;

    @ManyToOne
    @JoinColumn(name="recipe")
    private Recipe recipe;
}
