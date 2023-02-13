package com.b304.bobs.db.repository;

import com.b304.bobs.db.entity.RecipeLike;
import com.b304.bobs.db.entity.Refrige;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefrigeRepository extends JpaRepository<Refrige, Long> {

    @Query(value =
            "select r from Refrige r " +
                    "left join fetch r.ingredient i " +
                    "where r.user.user_id =:userId AND r.refrige_ingredient_delete = false " +
                    "order by i.ingredient_name asc", countQuery = "select count(*) from Refrige r where r.user.user_id =:userId AND r.refrige_ingredient_delete = false")
    Page<Refrige> findByUser(@Param("userId") Long user_id, Pageable pageable);

    @Modifying
    @Query(value = "UPDATE refrige SET refrige_ingredient_prior =:refrigeIngredientPrior WHERE refrige_id =:refrigeId AND refrige_ingredient_delete =0", nativeQuery = true)
    int modifyRefrige(@Param("refrigeIngredientPrior")Boolean refrige_ingredient_prior, @Param("refrigeId")Long refrige_id);

    @Query(value ="SELECT * FROM refrige WHERE ingredient_id =:ingredientId AND refrige_ingredient_delete = false", nativeQuery = true)
    Optional<Refrige> findByIngredientId(@Param("ingredientId") Long ingredient_id);

}
