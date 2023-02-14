package com.b304.bobs.api.service.Refrige;

import com.b304.bobs.api.request.Refrige.RefrigeReq;
import com.b304.bobs.api.response.PageRes;

public interface RefrigeService {
    boolean modifyRefrige(RefrigeReq refrigeReq) throws Exception;
    PageRes findByUser(Long user_id) throws Exception;

}