package com.b304.bobs.api.response;

import com.b304.bobs.db.entity.Ingredient;
import com.b304.bobs.db.entity.Recipe;
import lombok.Getter;

@Getter
public class RecipeRes {
    private Long recipe_id;
    private String recipe_name;
    private String recipe_amount;
    private String recipe_content;
    private int recipe_hit;
    private String recipe_img;
    private String recipe_level;
    private String getRecipe_time;
    private String recipe_type;
    private String recipe_category;

    public RecipeRes() {

    }

    public RecipeRes(Recipe recipe) {
        this.recipe_id =recipe.getRecipe_id();
        this.recipe_name = recipe.getRecipe_name();
        this.recipe_amount = recipe.getRecipe_amount();
        this.recipe_content = recipe.getRecipe_content();
        this.recipe_hit = recipe.getRecipe_hit();
        this.recipe_img = recipe.getRecipe_img();
        this.recipe_level = recipe.getRecipe_level();
        this.getRecipe_time = recipe.getRecipe_time();
        this.recipe_type = recipe.getRecipe_type();
        this.recipe_category = recipe.getRecipe_category();
    }

}
