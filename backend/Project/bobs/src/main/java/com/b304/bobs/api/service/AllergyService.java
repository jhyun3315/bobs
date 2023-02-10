package com.b304.bobs.api.service;

import com.b304.bobs.api.request.AllergyReq;
import com.b304.bobs.api.response.AllergyRes;

public interface AllergyService {
    public AllergyRes createAllergy(AllergyReq allergyReq) throws Exception;
}
