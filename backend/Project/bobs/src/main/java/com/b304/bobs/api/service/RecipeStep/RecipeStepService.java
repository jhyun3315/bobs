package com.b304.bobs.api.service.RecipeStep;

import com.b304.bobs.api.response.RecipeStep.RecipeStepRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecipeStepService {
    public List<RecipeStepRes> findById(Long recipe_id) throws Exception;
}
