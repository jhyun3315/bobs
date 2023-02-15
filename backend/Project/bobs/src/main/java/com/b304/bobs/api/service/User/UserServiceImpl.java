package com.b304.bobs.api.service.User;

import com.b304.bobs.api.response.NotUserRes;
import com.b304.bobs.api.response.User.UserRes;
import com.b304.bobs.db.entity.User;
import com.b304.bobs.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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

    @Override
    public boolean isUserExist(Long user_id) throws Exception {
        return userRepository.isUserExist(user_id).isPresent();

    }
}
