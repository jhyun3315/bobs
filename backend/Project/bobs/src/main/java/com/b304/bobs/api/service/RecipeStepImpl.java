package com.b304.bobs.api.service;

import com.b304.bobs.api.response.*;
import com.b304.bobs.db.entity.RecipeStep;
import com.b304.bobs.db.repository.RecipeStepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeStepImpl implements RecipeStepService{

    final private RecipeStepRepository recipeStepRepository;

    @Override
    public List<RecipeStepRes> findById(Long recipe_id) throws Exception {
        List<RecipeStepRes> recipeStepRes = new ArrayList<>();

        try {
            List<RecipeStep> recipes = recipeStepRepository.findStepById(recipe_id);
            if(recipes.isEmpty()) return recipeStepRes;

            for(RecipeStep step : recipes){
                recipeStepRes.add(new RecipeStepRes(step));
            }
            return recipeStepRes;
        }catch (Exception e){
            e.printStackTrace();
        }
        return recipeStepRes;
    }
}
