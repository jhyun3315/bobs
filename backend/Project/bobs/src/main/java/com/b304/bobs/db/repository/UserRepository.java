package com.b304.bobs.db.repository;

import com.b304.bobs.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM user WHERE user_email =:userEmail AND user_deleted = 0", nativeQuery = true)
    public Optional<User> findByEmail(@Param("userEmail") String user_email);

    // 회원 정보
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM user WHERE user_key =:userKey", nativeQuery = true)
    public User findUser(@Param("userKey") String user_key);

    // 회원 정보 name profile
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM user WHERE user_id =:userId", nativeQuery = true)
    public User findNP(@Param("userId") Long user_id);
}
