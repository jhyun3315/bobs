package com.b304.bobs.api.service;

import com.b304.bobs.api.request.AllergyReq;
import com.b304.bobs.api.response.PageRes;

public interface AllergyService {
    boolean createAllergy(AllergyReq allergyReq) throws Exception;
    PageRes findById(Long user_id) throws Exception;
}
