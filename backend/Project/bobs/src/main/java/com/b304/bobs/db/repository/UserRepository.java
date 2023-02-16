package com.b304.bobs.db.repository;

import com.b304.bobs.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM user WHERE user_email =:userEmail AND user_deleted = 0", nativeQuery = true)
    Optional<User> findByEmail(@Param("userEmail") String user_email);

    // 회원 정보
    @Query(value = "SELECT * FROM user WHERE user_key =:userKey", nativeQuery = true)
    User findUser(@Param("userKey") String user_key);

    // 회원 정보 name profile
    @Query(value = "SELECT * FROM user WHERE user_id =:userId", nativeQuery = true)
    User findNP(@Param("userId") Long user_id);

    @Query(value="SELECT * FROM user WHERE user_id=:userId AND user_deleted=0", nativeQuery = true)
    Optional<User> isUserExist(@Param("userId") Long user_id);


}
