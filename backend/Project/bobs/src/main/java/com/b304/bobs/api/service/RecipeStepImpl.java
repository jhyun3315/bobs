package com.b304.bobs.api.service;

import com.b304.bobs.api.response.*;
import com.b304.bobs.db.entity.RecipeStep;
import com.b304.bobs.db.repository.RecipeStepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeStepImpl implements RecipeStepService{

    final private RecipeStepRepository recipeStepRepository;

    @Override
    public PageRes findById(Long recipe_id) throws Exception {
        PageRes pageRes = new PageRes();

        try {
            List<RecipeStep> recipes = recipeStepRepository.findStepById(recipe_id);

            if(recipes.isEmpty()) return pageRes;
            pageRes
                    .setContents(recipes.stream()
                            .map(RecipeStepRes::new)
                            .collect(Collectors.toList())
                    );
        }catch (Exception e){
            e.printStackTrace();
        }
        return pageRes;
    }
}
