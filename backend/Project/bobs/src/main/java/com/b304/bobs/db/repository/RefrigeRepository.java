package com.b304.bobs.db.repository;

import com.b304.bobs.db.entity.Refrige;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RefrigeRepository extends JpaRepository<Refrige, Long> {

    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM refrige WHERE user_id =:userId AND refrige_ingredient_delete = 0", nativeQuery = true)
    public Page<Refrige> findByUser(@Param("userId") Long user_id, @PageableDefault(size = 20 ) Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "UPDATE refrige SET refrige_ingredient_prior =:refrigeIngredientPrior WHERE refrige_id =:refrigeId AND refrige_ingredient_delete =0", nativeQuery = true)
    public int modifyRefrige(@Param("refrigeIngredientPrior")Boolean refrige_ingredient_prior, @Param("refrigeId")Long refrige_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE refrige SET refrige_ingredient_delete = 1 WHERE refrige_id =:refrigeId AND refrige_ingredient_delete=0", nativeQuery = true)
    public int deleteRefrigeById(@Param("refrigeId") Long refrige_id);

}
