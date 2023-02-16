package com.b304.bobs.api.response;

import com.b304.bobs.db.entity.Recipe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecommendRes {

    private Long recipe_id;
    private String recipe_name;
    private String recipe_amount;
    private String recipe_content;
    private int recipe_hit;
    private String recipe_img;
    private String recipe_level;
    private String getRecipe_time;
    private int matchRatio;

    public RecommendRes(Long recipe_id, String recipe_name, String recipe_amount, String recipe_content, int recipe_hit, String recipe_img, String recipe_level, String getRecipe_time) {
        this.recipe_id = recipe_id;
        this.recipe_name = recipe_name;
        this.recipe_amount = recipe_amount;
        this.recipe_content = recipe_content;
        this.recipe_hit = recipe_hit;
        this.recipe_img = recipe_img;
        this.recipe_level = recipe_level;
        this.getRecipe_time = getRecipe_time;
        this.matchRatio = 0;
    }
}
