package com.b304.bobs.api.service;

import com.b304.bobs.api.request.UserReq;
import com.b304.bobs.api.response.NotUserRes;
import com.b304.bobs.api.response.UserRes;

public interface UserService {
    public UserRes findUser(String user_key) throws Exception;
    public NotUserRes findNP(Long user_id) throws Exception;
}
