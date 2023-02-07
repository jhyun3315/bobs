package com.b304.bobs.repository;

import com.b304.bobs.entity.Recipe;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RecipeRepository {

    @PersistenceContext
    private EntityManager em;

}
