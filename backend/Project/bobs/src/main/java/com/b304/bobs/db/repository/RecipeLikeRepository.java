package com.b304.bobs.db.repository;

import com.b304.bobs.db.entity.RecipeLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RecipeLikeRepository extends JpaRepository<RecipeLike, Long> {

    @Query(value =
            "select s from RecipeLike s " +
                    "left join fetch s.recipe r " +
                    "where s.user.user_id =:userId AND s.recipe_like_is_deleted = false " +
                    "order by s.recipe_like_created desc", countQuery = "select count(*) from RecipeLike")
    List<RecipeLike> findByUserLike(@Param("userId") Long user_id);

}
