package com.b304.bobs.api.response;

import com.b304.bobs.db.entity.Ingredient;
import com.b304.bobs.db.entity.Refrige;
import lombok.Getter;

@Getter
public class RefrigeRes {
    private Long refrige_id;
    private Long ingredient_id;
    private String ingredient_name;
    private Boolean refrige_ingredient_prior;

    public RefrigeRes() {

    }

    public RefrigeRes(Refrige refrige) {
        Ingredient ingredient = refrige.getIngredient();
        this.refrige_id = refrige.getRefrige_id();
        this.ingredient_id = ingredient.getIngredient_id();
        this.ingredient_name = ingredient.getIngredient_name();
        this.refrige_ingredient_prior = refrige.getRefrige_ingredient_prior();
    }

}
