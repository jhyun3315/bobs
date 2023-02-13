package com.b304.bobs.api.service;

import com.b304.bobs.api.response.*;
import com.b304.bobs.db.entity.Recipe;
import com.b304.bobs.db.entity.RecipeIngredient;
import com.b304.bobs.db.entity.RecipeLike;
import com.b304.bobs.db.entity.User;
import com.b304.bobs.db.repository.RecipeIngredientRepository;
import com.b304.bobs.db.repository.RecipeLikeRepository;
import com.b304.bobs.db.repository.RecipeRepository;
import com.b304.bobs.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService{
    final private RecipeRepository recipeRepository;
    final private RecipeLikeRepository recipeLikeRepository;
    final private RecipeIngredientRepository recipeIngredientRepository;
    final private UserRepository userRepository;
    private final StringRedisTemplate redisTemplate;

    @Override
    @CacheEvict(cacheNames = "recipes", key = "findAll")
    public void recipeLike(Long userId, Long recipe_like_id){
        Recipe recipe = recipeRepository.findById(recipe_like_id).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        Optional<RecipeLike> recipeLike = recipeLikeRepository.findByUserIdAndRecipeId(userId, recipe_like_id);

        if (recipeLike.isPresent()){
            //cancel like
            recipeLikeRepository.delete(recipeLike.get());
            redisTemplate.opsForValue().increment("recipe:" + recipe_like_id + ":hits", -1);
            recipe.setRecipe_hit(recipe.getRecipe_hit() - 1);
            recipeRepository.save(recipe);
        } else {
            // create like
            RecipeLike result = new RecipeLike();
            result.setRecipe(recipe);
            result.setUser(user);
            recipeLikeRepository.save(result);
            redisTemplate.opsForValue().increment("recipe:" + recipe_like_id + ":hits", 1);
            recipe.setRecipe_hit(recipe.getRecipe_hit() + 1);
            recipeRepository.save(recipe);
        }
    }

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
    @Cacheable("recipes")
    public PageRes findAll() throws Exception {
        System.out.println("Finding all recipes");
        PageRes pageRes = new PageRes();

        try {
            List<Recipe> recipes = recipeRepository.findAll();

            if(recipes.isEmpty()) return pageRes;
            pageRes
                    .setContents(recipes.stream()
                            .map(RecipeRes::new)
                            .collect(Collectors.toList())
                    );
        }catch (Exception e){
            e.printStackTrace();
        }
        return pageRes;
    }

    @Override
    public PageRes findByUserLike(Long user_id) throws Exception {
        PageRes pageRes = new PageRes();

        try {
            List<RecipeLike> recipeLikes = recipeLikeRepository.findByUserLike(user_id);
            pageRes.setContents(recipeLikes.stream()
                            .map(RecipeLikeRes::new)
                            .collect(Collectors.toList())
                    );

        }catch (Exception e){
            e.printStackTrace();
        }
        return pageRes;
    }

    @Transactional(readOnly = true)
    @Override
    public PageRes findIngredientsById(Long recipe_id) throws Exception {
        PageRes pageRes = new PageRes();

        try {
            List<RecipeIngredient> ingredients = recipeIngredientRepository.findIngredientsById(recipe_id);
            System.out.println(ingredients.get(0).getRecipe_ingredient());

            if(ingredients.isEmpty()) return pageRes;
            pageRes
                    .setContents(ingredients.stream()
                            .map(RecipeIngredientRes::new)
                            .collect(Collectors.toList())
                    );
            pageRes.setTotalPages(0);
        }catch (Exception e){
            e.printStackTrace();
        }
        return pageRes;
    }

}
