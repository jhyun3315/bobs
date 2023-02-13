package com.b304.bobs.api.service;

import com.b304.bobs.api.request.RefrigeReq;
import com.b304.bobs.api.response.PageRes;
import org.springframework.data.domain.Pageable;

public interface RefrigeService {
    boolean modifyRefrige(RefrigeReq refrigeReq) throws Exception;
    PageRes findByUser(Long user_id) throws Exception;

}