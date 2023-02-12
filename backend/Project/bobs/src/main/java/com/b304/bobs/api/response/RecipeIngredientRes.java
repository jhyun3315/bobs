package com.b304.bobs.api.response;

import com.b304.bobs.db.entity.RecipeIngredient;
import lombok.Getter;

@Getter
public class RecipeIngredientRes {
    private String recipe_ingredient;
    private String recipe_ingredient_amount;
    private String recipe_ingredient_type;

    public RecipeIngredientRes() {
    }

    public RecipeIngredientRes(RecipeIngredient recipeIngredient) {
        this.recipe_ingredient = recipeIngredient.getRecipe_ingredient();
        this.recipe_ingredient_amount = recipeIngredient.getRecipe_ingredient_amount();
        this.recipe_ingredient_type = recipeIngredient.getRecipe_ingredient_type();
    }
}
