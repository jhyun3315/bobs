package com.b304.bobs.api.service.RecipeStep;

import com.b304.bobs.api.response.RecipeStep.RecipeStepRes;
import com.b304.bobs.db.entity.RecipeStep;
import com.b304.bobs.db.repository.RecipeStepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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
