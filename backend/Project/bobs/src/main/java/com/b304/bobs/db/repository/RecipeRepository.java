package com.b304.bobs.db.repository;

import com.b304.bobs.db.entity.Recipe;
import com.b304.bobs.db.entity.RecipeLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query(value = "SELECT * FROM recipe ORDER BY recipe_hit DESC", nativeQuery = true)
    Page<Recipe> findAll(@PageableDefault(size = 20) Pageable pageable);

    @Query(value = "SELECT * FROM recipe WHERE recipe_id =:recipeId", nativeQuery= true)
    Recipe findOneById(@Param("recipeId") Long recipe_id);

  
}
