package com.b304.bobs.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="recipe")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    @Id
    @Column(name="recipe_id")
    private Long recipe_id;

    @Column(name="recipe_name")
    private String recipe_name;

    @Column(name="recipe_content")
    private String recipe_content;

    @Column(name="recipe_img")
    private String recipe_img;

    @Column(name="recipe_time")
    private String recipe_time;

    @Column(name="recipe_amount")
    private String recipe_amount;

    @Column(name="reciep_level")
    private String recipe_level;

    @Column(name="recipe_category")
    private String recipe_category;

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
