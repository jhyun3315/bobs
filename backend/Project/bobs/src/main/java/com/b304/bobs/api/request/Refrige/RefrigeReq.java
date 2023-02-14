package com.b304.bobs.api.request.Refrige;

import com.b304.bobs.db.entity.Refrige;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class RefrigeReq {
    private Long user_id;
    private List<Map<String,String>> ingredient_list;

    public RefrigeReq() {

    }

}