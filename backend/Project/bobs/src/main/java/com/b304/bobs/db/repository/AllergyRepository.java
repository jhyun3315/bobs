package com.b304.bobs.db.repository;

import com.b304.bobs.db.entity.Allergy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AllergyRepository extends JpaRepository<Allergy, Long> {

    @Query(value ="SELECT * FROM allergy WHERE ingredient_id =:ingredientId", nativeQuery = true)
    Optional<Allergy> findByIngredientId(@Param("ingredientId") Long allergyId);

    @Query(value ="SELECT * FROM allergy WHERE user_id =:userId AND is_deleted= 0",nativeQuery = true)
    List<Allergy> findByUserId(@Param("userId") Long user_id);

}
