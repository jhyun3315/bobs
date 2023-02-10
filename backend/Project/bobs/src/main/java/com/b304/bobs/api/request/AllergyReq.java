package com.b304.bobs.api.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Data
public class AllergyReq {
    private Long user_id;
    private String user_name;
    private Long allergy_id;
    private Long ingredient_id;
    private String ingredient_name;
    private boolean is_deleted;

    public AllergyReq(){}

    public AllergyReq(Long user_id, String user_name, Long allergy_id, boolean is_deleted, Long ingredient_id, String ingredient_name) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.allergy_id = allergy_id;
        this.ingredient_id = ingredient_id;
        this.ingredient_name = ingredient_name;
    }
}
