package com.b304.bobs.repository;
import com.b304.bobs.entity.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {

    private static Long seq = 1L;
    private static Map<Long, User> table = new ConcurrentHashMap<>();

    public User save(User user) {
        if (user.getUser_id() == null) {
            user.setUser_id(seq);
            table.put(seq++, user);
        } else {
            table.replace(user.getUser_id(), user);
        }

        return user;
    }

    public List<User> findAll() {
        Collection<User> values = table.values();
        return values.size() == 0 ? new ArrayList<>() : new ArrayList<>(values);
    }

    public Optional<User> findByEmail(String email) {
        for (User user : table.values()) {
            if (user.getUser_email().equals(email)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
