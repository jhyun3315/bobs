package com.b304.bobs.api.response;

import com.b304.bobs.db.entity.Recipe;
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
    private String name;
    private String recipe_time;
    private String recipe_type;
    private Long recipe_like_id;
    private LocalDateTime recipe_like_created;
    private boolean recipe_like_is_deleted;


}
