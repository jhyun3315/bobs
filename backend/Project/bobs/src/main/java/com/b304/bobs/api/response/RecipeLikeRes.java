package com.b304.bobs.api.response;

import com.b304.bobs.db.entity.Recipe;
import com.b304.bobs.db.entity.RecipeLike;
import com.b304.bobs.db.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class RecipeLikeRes {
    private Long recipe_id;
    private Long user_id;
    private String recipe_amount;
    private String recipe_category;
    private String recipe_content;
    private int recipe_hit;
    private String recipe_img;
    private String recipe_level;
    private String recipe_name;
    private String recipe_time;
    private String recipe_type;
    private Long recipe_like_id;
    private boolean recipe_like_is_deleted;

    public RecipeLikeRes() {
    }

    public RecipeLikeRes(RecipeLike recipeLike) {
        Recipe recipe = recipeLike.getRecipe();

        this.recipe_id = recipe.getRecipe_id();
        this.recipe_amount = recipe.getRecipe_amount();
        this.recipe_category = recipe.getRecipe_category();
        this.recipe_content = recipe.getRecipe_content();
        this.recipe_hit = recipe.getRecipe_hit();
        this.recipe_img = recipe.getRecipe_img();
        this.recipe_level = recipe.getRecipe_level();
        this.recipe_name = recipe.getRecipe_name();
        this.recipe_time = recipe.getRecipe_time();
        this.recipe_type = recipe.getRecipe_type();
        this.recipe_like_id = recipeLike.getRecipe_like_id();
        this.recipe_like_is_deleted = recipeLike.isRecipe_like_is_deleted();
    }


}
