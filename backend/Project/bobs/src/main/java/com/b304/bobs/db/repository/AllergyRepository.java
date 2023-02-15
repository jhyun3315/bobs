package com.b304.bobs.db.repository;

import com.b304.bobs.db.entity.Allergy;;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface AllergyRepository extends JpaRepository<Allergy, Long> {

    @Query(value ="SELECT * FROM allergy WHERE ingredient_id =:ingredientId", nativeQuery = true)
    Optional<Allergy> findByIngredientId(@Param("ingredientId") Long allergyId);

    @Query(value ="SELECT * FROM allergy WHERE user_id =:userId AND is_deleted= 0",nativeQuery = true)
    List<Allergy> findByUserId(@Param("userId") Long user_id);

    @Query(value = "SELECT i.ingredient_name " +
            "FROM allergy a " +
            "JOIN user u ON a.user_id = u.user_id " +
            "JOIN ingredient i ON a.ingredient_id = i.ingredient_id " +
            "WHERE u.user_id =:userId AND a.is_deleted = 0", nativeQuery = true)
    List<Allergy> findIngredientByUserId(@Param("userId") Long user_id);

}