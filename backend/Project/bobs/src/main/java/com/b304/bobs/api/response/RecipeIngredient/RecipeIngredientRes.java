package com.b304.bobs.api.response.RecipeIngredient;

import com.b304.bobs.db.entity.RecipeIngredient;
import lombok.Getter;

@Getter
public class RecipeIngredientRes {
    private String recipe_ingredient;
    private String recipe_ingredient_amount;

    public RecipeIngredientRes() {
    }

    public RecipeIngredientRes(RecipeIngredient recipeIngredient) {
        this.recipe_ingredient = recipeIngredient.getRecipe_ingredient();
        this.recipe_ingredient_amount = recipeIngredient.getRecipe_ingredient_amount();
    }
}
