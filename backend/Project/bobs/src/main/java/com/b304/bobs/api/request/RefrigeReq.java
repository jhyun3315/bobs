package com.b304.bobs.api.request;

import com.b304.bobs.db.entity.Refrige;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Data
public class RefrigeReq {
    private Long refrige_id;
    private Boolean refrige_ingredient_delete;
    private Boolean refrige_ingredient_prior;
    private Long ingredient_id;
    private Long user_id;

    public RefrigeReq() {

    }

    public RefrigeReq(Refrige refrige) {
        this.refrige_id = refrige.getRefrige_id();
        this.refrige_ingredient_prior = refrige.getRefrige_ingredient_prior();
        this.refrige_ingredient_delete = refrige.getRefrige_ingredient_delete();
        this.ingredient_id = refrige.getIngredient().getIngredient_id();
        this.user_id = refrige.getUser().getUser_id();
    }
}
