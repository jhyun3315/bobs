package com.b304.bobs.api.response.RecipeStep;

import com.b304.bobs.db.entity.RecipeStep;
import lombok.Getter;

@Getter
public class RecipeStepRes {
    private int recipe_num;
    private String recipe_step_content;
    private String recipe_img;

    public RecipeStepRes() {
    }

    public RecipeStepRes(RecipeStep recipeStep) {
        this.recipe_num = recipeStep.getRecipe_step_num();
        this.recipe_step_content = recipeStep.getRecipe_step_content();
        this.recipe_img = recipeStep.getRecipe_step_img();
    }
}
