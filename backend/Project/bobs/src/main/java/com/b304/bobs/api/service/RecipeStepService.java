package com.b304.bobs.api.service;

import com.b304.bobs.api.response.PageRes;
import org.springframework.stereotype.Service;

@Service
public interface RecipeStepService {
    public PageRes findById(Long recipe_id) throws Exception;
}
