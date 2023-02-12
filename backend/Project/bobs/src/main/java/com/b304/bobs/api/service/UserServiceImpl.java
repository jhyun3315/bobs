package com.b304.bobs.api.service;

import com.b304.bobs.api.response.NotUserRes;
import com.b304.bobs.api.response.UserRes;
import com.b304.bobs.db.entity.User;
import com.b304.bobs.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserRes findUser(String user_key) throws Exception {

        UserRes userRes = new UserRes();
        try {
            User user = userRepository.findUser(user_key);

            if (user == null) return userRes;
            else return new UserRes(user);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userRes;
    }

    @Override
    public NotUserRes findNP(Long user_id) throws Exception {
        NotUserRes notUserRes = new NotUserRes();

        try {
            User user = userRepository.findNP(user_id);

            if (user == null) return notUserRes;
            else return new NotUserRes(user);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return notUserRes;
    }
}
