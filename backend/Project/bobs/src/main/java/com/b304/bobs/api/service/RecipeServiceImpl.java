package com.b304.bobs.api.service;

import com.b304.bobs.api.response.*;
import com.b304.bobs.db.entity.Recipe;
import com.b304.bobs.db.entity.RecipeLike;
import com.b304.bobs.db.entity.User;
import com.b304.bobs.db.repository.RecipeLikeRepository;
import com.b304.bobs.db.repository.RecipeRepository;
import com.b304.bobs.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService{
    final private RecipeRepository recipeRepository;
    final private RecipeLikeRepository recipeLikeRepository;
    final private UserRepository userRepository;

    @Override
    public RecipeRes findOneById(Long recipe_id) throws Exception {
        RecipeRes recipeRes = new RecipeRes();

        try {
            Recipe recipe = recipeRepository.findOneById(recipe_id);
            if(recipe.getRecipe_id()==null) return recipeRes;
            else recipeRes = new RecipeRes(recipe);
        }catch (Exception e){
            e.printStackTrace();
        }
        return recipeRes;
    }

    @Override
    public PageRes findAll(Pageable pageable) throws Exception {
        PageRes pageRes = new PageRes();

        try {
            Page<Recipe> recipes = recipeRepository.findAll(pageable);

            if(recipes.isEmpty()) return pageRes;
            pageRes
                    .setContents(recipes.stream()
                            .map(RecipeRes::new)
                            .collect(Collectors.toList())
                    );
            pageRes.setTotalPages(recipes.getTotalPages());
        }catch (Exception e){
            e.printStackTrace();
        }
        return pageRes;
    }

    @Override
    public PageRes findByUserLike(Long user_id, Pageable pageable) throws Exception {
        PageRes pageRes = new PageRes();

        try {
//            User user = userRepository.findById(user_id).orElse(null);
//            if(user == null) return pageRes;
//            else {
                Page<RecipeLike> recipeLikes = recipeLikeRepository.findByUserLike(user_id, pageable);
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");

                pageRes
                        .setContents(recipeLikes.stream()
                                .map(RecipeLikeRes::new)
                                .collect(Collectors.toList())
                        );
                pageRes.setTotalPages(recipeLikes.getTotalPages());
//            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return pageRes;
    }
}
