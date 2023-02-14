package com.b304.bobs.api.response.Ingredient;

import com.b304.bobs.db.entity.Ingredient;
import lombok.Getter;

@Getter
public class IngredientRes {
    private Long ingredient_id;
    private String ingredient_name;

    public IngredientRes() {
    }


    public IngredientRes(Ingredient ingredient) {
        this.ingredient_id = ingredient.getIngredient_id();
        this.ingredient_name = ingredient.getIngredient_name();
    }
}
