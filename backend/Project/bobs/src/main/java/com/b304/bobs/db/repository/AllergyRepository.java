package com.b304.bobs.db.repository;

import com.b304.bobs.db.entity.Allergy;;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface AllergyRepository extends JpaRepository<Allergy, Long> {

    @Query(value ="SELECT * FROM allergy WHERE ingredient_id =:ingredientId AND user_id =:userId", nativeQuery = true)
    Optional<Allergy> findByIngredientIdAndUser(@Param("ingredientId") Long ingredient_id, @Param("userId") Long user_id);

    @Query(value ="SELECT * FROM allergy WHERE user_id =:userId AND is_deleted= 0",nativeQuery = true)
    List<Allergy> findByUserId(@Param("userId") Long user_id);

    @Query(value = "SELECT i.ingredient_name " +
            "FROM allergy a " +
            "JOIN user u ON a.user_id = u.user_id " +
            "JOIN ingredient i ON a.ingredient_id = i.ingredient_id " +
            "WHERE u.user_id =:userId AND a.is_deleted = 0", nativeQuery = true)
    List<Allergy> findIngredientByUserId(@Param("userId") Long user_id);

    @Modifying
    @Query(value="UPDATE allergy SET is_deleted =:isDeleted WHERE user_id =:userId", nativeQuery = true)
    int updateDeleted(@Param("isDeleted") int is_deleted, @Param("userId") Long user_id);

}