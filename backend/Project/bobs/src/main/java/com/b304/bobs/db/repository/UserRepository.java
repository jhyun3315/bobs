package com.b304.bobs.db.repository;

import com.b304.bobs.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM user WHERE user_email =:userEmail AND user_deleted = 0", nativeQuery = true)
    Optional<User> findByEmail(@Param("userEmail") String user_email);

    @Query(value = "SELECT * FROM user WHERE user_id =:userId AND user_deleted = 0", nativeQuery = true)
    User findOneById(@Param("userId") Long id);

}
