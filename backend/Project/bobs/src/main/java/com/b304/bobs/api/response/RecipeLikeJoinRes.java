package com.b304.bobs.api.response;

import java.time.LocalDateTime;

public class RecipeLikeJoinRes {
    private Long recipe_id;
    private String recipe_amount;
    private String recipe_category;
    private String recipe_content;
    private int recipe_hit;
    private String recipe_img;
    private String recipe_level;
    private String name;
    private String recipe_time;
    private String recipe_type;
    private Long recipe_like_id;
    private LocalDateTime recipe_like_created;

    public RecipeLikeJoinRes(RecipeLikeRes recipeLikeRes) {
        this.recipe_id = recipeLikeRes.getRecipe_id();
        this.recipe_amount = recipeLikeRes.getRecipe_amount();
        this.recipe_category = recipeLikeRes.getRecipe_category();
        this.recipe_content = recipeLikeRes.getRecipe_content();
        this.recipe_hit = recipeLikeRes.getRecipe_hit();
        this.recipe_img = recipeLikeRes.getRecipe_img();
        this.recipe_level = recipeLikeRes.getRecipe_level();
        this.name = recipeLikeRes.getName();
        this.recipe_time = recipeLikeRes.getRecipe_time();
        this.recipe_type = recipeLikeRes.getRecipe_type();
        this.recipe_like_id = recipeLikeRes.getRecipe_like_id();
        this.recipe_like_created = recipeLikeRes.getRecipe_like_created();
    }
}
