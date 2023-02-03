package com.b304.bobs.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="recipe")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    @Id
    @Column(name="recipe_id",columnDefinition = "INT", nullable = false)
    private int recipe_id;

    @Column(name="recipe_name",columnDefinition = "VARCHAR(20)", nullable = false)
    private String recipe_name;

    @Column(name="recipe_content",columnDefinition = "VARCHAR(100)")
    private String recipe_content;

    @Column(name="recipe_img",columnDefinition = "VARCHAR(100)")
    private String recipe_img;

    @Column(name="recipe_time",columnDefinition = "DATETIME", nullable = false)
    private String recipe_time;

    @Column(name="recipe_amount",columnDefinition = "VARCHAR(10)")
    private String recipe_amount;

    @Column(name="reciep_level",columnDefinition = "VARCHAR(10)")
    private String recipe_level;

    @Column(name="recipe_category",columnDefinition = "VARCHAR(10)")
    private String recipe_category;

    @Column(name="recipe_hit", columnDefinition = "INT", nullable = false)
    private int recipe_hit;

    @OneToMany(mappedBy = "recipe")
    List<RecipeStep> recipe_steps = new ArrayList<RecipeStep>();

    @OneToMany(mappedBy = "recipe")
    List<RecipeIngredient> recipe_ingredients = new ArrayList<RecipeIngredient>();

    public void addRecipeStep(RecipeStep recipeStep){
        recipe_steps.add(recipeStep);
        recipeStep.setRecipe(this);
    }

    public void addRecipeIngredient(RecipeIngredient recipeIngredient){
        recipe_ingredients.add(recipeIngredient);
        recipeIngredient.setRecipe(this);
    }
}
