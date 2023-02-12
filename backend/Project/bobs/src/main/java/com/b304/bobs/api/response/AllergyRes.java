package com.b304.bobs.api.response;

import com.b304.bobs.api.request.AllergyReq;
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

}
