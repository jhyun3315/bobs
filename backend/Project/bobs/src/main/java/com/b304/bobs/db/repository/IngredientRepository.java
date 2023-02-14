package com.b304.bobs.db.repository;

import com.b304.bobs.db.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,Long> {

    @Query(value = "SELECT * FROM ingredient", nativeQuery = true)
    List<Ingredient> findAll();
}
