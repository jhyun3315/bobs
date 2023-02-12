package com.b304.bobs.api.service;

import com.b304.bobs.api.request.RefrigeReq;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import org.springframework.data.domain.Pageable;

public interface RefrigeService {

    public RefrigeReq createRefrige(RefrigeReq refrigeReq) throws Exception;
    public ModifyRes deleteRefrige(Long refrige_id) throws Exception;
    public ModifyRes modifyRefrige(RefrigeReq refrigeReq) throws Exception;
    public PageRes findByUser(Long user_id, Pageable pageable) throws Exception;
}
