package com.b304.bobs.api.request.Allergy;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter @Setter
@Data
public class AllergyReq {
    private Long user_id;
    private List<Map<String,String>> allergy_list;

    public AllergyReq(){}


}
