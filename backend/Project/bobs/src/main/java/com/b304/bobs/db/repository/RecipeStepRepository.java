package com.b304.bobs.db.repository;

import com.b304.bobs.db.entity.RecipeStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RecipeStepRepository extends JpaRepository<RecipeStep, Long> {
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM recipe_step WHERE recipe_id =:recipeId ORDER BY recipe_step_num ASC", nativeQuery= true)
    public List<RecipeStep> findStepById(@Param("recipeId") Long recipe_id);
}
