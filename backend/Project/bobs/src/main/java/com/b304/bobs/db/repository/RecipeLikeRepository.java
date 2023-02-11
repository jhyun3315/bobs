package com.b304.bobs.db.repository;

import com.b304.bobs.db.entity.RecipeLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeLikeRepository extends JpaRepository<RecipeLike, Long> {

//    @Query(value = "SELECT * FROM RecipeLike WHERE user_id =:userId AND recipe_like_is_deleted =0 ", nativeQuery = true)
//    List<RecipeLike> findByUserId(@Param("userId") Long userId);

    @Query(value =
            "select s from RecipeLike s " +
                    "left join fetch s.recipe r " +
                    "left join fetch s.user u " +
                    "where u.user_id =:userId AND s.recipe_like_is_deleted = false " +
                    "order by s.recipe_like_created desc ", countQuery = "select count(*) from RecipeLike")
    Page<RecipeLike> findByUserLike(@Param("userId") Long userId, Pageable pageable);

}
