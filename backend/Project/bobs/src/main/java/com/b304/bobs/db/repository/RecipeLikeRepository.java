package com.b304.bobs.db.repository;

import com.b304.bobs.db.entity.RecipeLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeLikeRepository extends JpaRepository<RecipeLike, Long> {

    @Query(value =
            "select s from RecipeLike s " +
                    "left join fetch s.recipe r " +
                    "where s.user.user_id =:userId AND s.recipe_like_is_deleted = false " +
                    "order by s.recipe_like_created desc", countQuery = "select count(*) from RecipeLike s where s.user.user_id =:userId AND s.recipe_like_is_deleted = false ")
    List<RecipeLike> findByUserLike(@Param("userId") Long user_id);

    @Query(value = "SELECT * FROM recipe_like WHERE user_id =:userId AND recipe_id =:recipeId", nativeQuery = true)
    Optional<RecipeLike> findByUserIdAndRecipeId(@Param("userId") Long userId, @Param("recipeId") Long recipeId);
}
