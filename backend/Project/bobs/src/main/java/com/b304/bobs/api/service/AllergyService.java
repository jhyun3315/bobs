package com.b304.bobs.api.service;

import com.b304.bobs.api.request.AllergyReq;
import com.b304.bobs.api.response.AllergyRes;
import com.b304.bobs.api.response.PageRes;
import org.springframework.data.domain.Pageable;

public interface AllergyService {
    boolean createAllergy(AllergyReq allergyReq) throws Exception;
    PageRes findById(Long user_id, Pageable pageable) throws Exception;
}
