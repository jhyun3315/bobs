package com.b304.bobs.api.response;

import com.b304.bobs.db.entity.Allergy;
import lombok.Getter;

@Getter
public class AllergyRes {
    private String user_name;
    private Long allergy_id;
    private String ingredient_name;
    private boolean id_deleted;

    public AllergyRes() {
    }

    public AllergyRes(Allergy allergy) {
        this.user_name = allergy.getAllergy_name();
        this.allergy_id = allergy.getAllergy_id();
        this.ingredient_name = allergy.getAllergy_name();
        this.id_deleted = allergy.is_deleted();
    }


}
