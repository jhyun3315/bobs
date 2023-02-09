package com.b304.bobs.db.repository;

import com.b304.bobs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM user WHERE user_email =:userEmail AND user_deleted = 0", nativeQuery = true)
    public Optional<User> findByEmail(@Param("userEmail") String user_email);
}
