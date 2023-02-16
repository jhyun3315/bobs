package com.b304.bobs.db.repository;

import com.b304.bobs.db.entity.Refrige;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefrigeRepository extends JpaRepository<Refrige, Long> {

    @Query(value =
            "select r from Refrige r " +
                    "left join fetch r.ingredient i " +
                    "where r.user.user_id =:userId AND r.refrige_ingredient_delete = false " +
                    "order by i.ingredient_name asc")
    List<Refrige> findByUser(@Param("userId") Long user_id);

    @Modifying
    @Query(value = "UPDATE refrige SET refrige_ingredient_prior =:isPrior, refrige_ingredient_delete =:isDeleted  WHERE user_id =:userId", nativeQuery = true)
    int modifyRefrige(@Param("isPrior") int is_prior, @Param("isDeleted") int is_deleted, @Param("userId") Long user_id);

    @Query(value ="SELECT * FROM refrige WHERE ingredient_id =:ingredientId AND user_id =:userId", nativeQuery = true)
    Optional<Refrige> isExistIngredient(@Param("ingredientId") Long ingredient_id, @Param("userId") Long user_id);


    @Query(value = "SELECT * " +
            "FROM refrige r " +
            "JOIN ingredient i ON r.ingredient_id = i.ingredient_id " +
            "WHERE r.user_id =:userId AND r.refrige_ingredient_prior = 1 AND r.refrige_ingredient_delete = 0", nativeQuery = true)
    List<Refrige> findPriorByUser(@Param("userId") Long user_id);

    @Query(value = "SELECT * " +
            "FROM refrige r " +
            "JOIN ingredient i ON r.ingredient_id = i.ingredient_id " +
            "WHERE r.user_id =:userId AND r.refrige_ingredient_prior = 0 AND r.refrige_ingredient_delete = 0", nativeQuery = true)
    List<Refrige> findNomalByUser(@Param("userId") Long user_id);

    @Query(value = "SELECT * " +
            "FROM refrige r " +
            "JOIN ingredient i ON r.ingredient_id = i.ingredient_id " +
            "WHERE r.user_id =:userId AND r.refrige_ingredient_delete = 0", nativeQuery = true)
    List<Refrige> findByUserId(@Param("userId") Long user_id);
}
