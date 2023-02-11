package com.b304.bobs.db.repository;

import com.b304.bobs.api.response.RecipeLikeRes;
import com.b304.bobs.db.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM recipe ORDER BY recipe_hit DESC", nativeQuery = true)
    public Page<Recipe> findAll(@PageableDefault(size = 20) Pageable pageable);

    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM recipe WHERE recipe_id =:recipeId", nativeQuery= true)
    public Recipe findOneById(@Param("recipeId") Long recipe_id);

    @Transactional(readOnly = true)
    @Query(value =
            "SELECT recipe.*, recipe_like.recipe_like_id, b.recipe_like_created, b.recipe_like_is_deleted FROM recipe as a" +
                    " LEFT OUTER JOIN recipe_like as b ON a.recipe_id = b.recipe_id " +
                    "WHERE user_id =:userId AND recipe_like_is_deleted =0 " +
                    "ORDER BY recipe_like_created DESC", nativeQuery= true)
    public Page<RecipeLikeRes> findByUserLike(@Param("userId") Long userId, Pageable pageable);

}
