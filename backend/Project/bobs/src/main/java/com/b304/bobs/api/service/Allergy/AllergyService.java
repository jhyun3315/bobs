package com.b304.bobs.api.service.Allergy;

import com.b304.bobs.api.request.Allergy.AllergyReq;
import com.b304.bobs.api.response.PageRes;

public interface AllergyService {
    boolean createAllergy(AllergyReq allergyReq) throws Exception;
    PageRes findById(Long user_id) throws Exception;
}
