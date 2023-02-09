package com.b304.bobs.db.repository;

import com.b304.bobs.db.entity.Refrige;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RefrigeRepository extends JpaRepository<Refrige, Long> {

    /*
    유저의 냉장고 재료 조회(일반)
     */
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM refrige where user_id =:userId and refrige_ingredient_delete = 0 and refrige_ingredient_prior = 0", nativeQuery = true)
    public Refrige findAll(@Param("userId")Long user_id);

}
