package com.b304.bobs.db.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RecipeRepository {

    @PersistenceContext
    private EntityManager em;

}
