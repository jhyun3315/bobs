package com.b304.bobs.db.repository;

import com.b304.bobs.db.entity.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IngredientRepository extends JpaRepository<Ingredient,Long> {

    @Query(value = "SELECT * FROM ingredient", nativeQuery = true)
    Page<Ingredient> findAll(Pageable pageable);
}
