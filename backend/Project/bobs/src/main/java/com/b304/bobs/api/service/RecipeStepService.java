package com.b304.bobs.api.service;

import com.b304.bobs.api.response.RecipeStepRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecipeStepService {
    public List<RecipeStepRes> findById(Long recipe_id) throws Exception;
}
