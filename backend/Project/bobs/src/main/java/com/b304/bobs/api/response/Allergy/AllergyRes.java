package com.b304.bobs.api.response.Allergy;

import com.b304.bobs.db.entity.Allergy;
import com.b304.bobs.db.entity.Ingredient;
import lombok.Getter;

@Getter
public class AllergyRes {
    private Long allergy_id;
    private Long ingredient_id;
    private String ingredient_name;
    private boolean id_deleted;

    public AllergyRes() {
    }

    public AllergyRes(Allergy allergy) {
        Ingredient ingredient = allergy.getIngredient();

        this.allergy_id = allergy.getAllergy_id();
        this.ingredient_id = ingredient.getIngredient_id();
        this.ingredient_name = allergy.getAllergy_name();
        this.id_deleted = allergy.is_deleted();
    }


}
