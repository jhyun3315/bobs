package com.b304.bobs.api.response;

import com.b304.bobs.api.request.AllergyReq;
import com.b304.bobs.db.entity.Allergy;
import lombok.Getter;

@Getter
public class AllergyRes {
    private String user_name;
    private Long allergy_id;
    private String ingredient_name;

    public AllergyRes() {
    }
    public AllergyRes(AllergyReq allergyReq) {
        this.user_name = allergyReq.getUser_name();
        this.allergy_id = allergyReq.getAllergy_id();
        this.ingredient_name = allergyReq.getIngredient_name();
    }

    public AllergyRes(Allergy allergy) {
        this.user_name = allergy.getUser().getUser_name();
        this.allergy_id = allergy.getAllergy_id();
        this.ingredient_name = allergy.getIngredient().getIngredient_name();
    }
}
