package com.b304.bobs.api.service.User;

import com.b304.bobs.api.response.NotUserRes;
import com.b304.bobs.api.response.User.UserRes;

public interface UserService{
    UserRes findUser(String user_key) throws Exception;
    NotUserRes findNP(Long user_id) throws Exception;
    boolean isUserExist(Long user_id) throws Exception;
}
