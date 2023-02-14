package com.b304.bobs.db.repository;

import com.b304.bobs.db.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {
    @Query(value = "SELECT * FROM recipe_ingredient WHERE recipe_id =:recipeId", nativeQuery = true)
    List<RecipeIngredient> findIngredientsById(@Param("recipeId") Long recipe_id);
}
