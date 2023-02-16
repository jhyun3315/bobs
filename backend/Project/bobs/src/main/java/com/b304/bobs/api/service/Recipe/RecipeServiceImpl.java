package com.b304.bobs.api.service.Recipe;

import com.b304.bobs.api.request.Recommend.RecommendReq;
import com.b304.bobs.api.response.*;
import com.b304.bobs.api.response.Recipe.RecipeRes;
import com.b304.bobs.api.response.RecipeIngredient.RecipeIngredientRes;
import com.b304.bobs.api.response.RecipeLike.RecipeLikeRes;
import com.b304.bobs.db.entity.*;
import com.b304.bobs.db.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService{
    final private RecipeRepository recipeRepository;
    final private RecipeLikeRepository recipeLikeRepository;
    final private RecipeIngredientRepository recipeIngredientRepository;
    final private UserRepository userRepository;
    final private AllergyRepository allergyRepository;
    final private RefrigeRepository refrigeRepository;
    private final StringRedisTemplate redisTemplate;

    @Override
    public List<RecommendRes> getRecommendedRecipesByUser(RecommendReq recommendReq) {

        List<Recipe> recipes = recipeRepository.findAll();
        List<RecommendRes> recommendations = new ArrayList<>();
        List<RecommendRes> recommendtest = new ArrayList<>();

        // Get user's allergies
        List<Allergy> allergies = allergyRepository.findByUserId(recommendReq.getUser_id());

        // Get user's fridge ingredients
        List<Refrige> refriges = refrigeRepository.findByUserId(recommendReq.getUser_id());

        // Calculate match ratio for each recipe
        for (Recipe recipe : recipes) {
            int matchRatio = 0;
            int percentage = 0;
            double score = 0.0;
            double score_percentage = 0.0;

            List<RecipeIngredient> recipeIngredients = recipe.getRecipe_ingredients();
            for (RecipeIngredient recipeIngredient : recipeIngredients) {
                for (Allergy allergy : allergies) {
                    if (recipeIngredient.getRecipe_ingredient().contains(allergy.getIngredient().getIngredient_name())) {
                        matchRatio = 0; // Don't call recipes with allergy ingredient
                        break;
                    }
                }
                int index = 0;
                for (Refrige refrige : refriges) {
                    int recipeIngredientLength = recipeIngredient.getRecipe_ingredient().length(); // 레시피 재료 개수
                    if (!refrige.getRefrige_ingredient_delete() && recipeIngredient.getRecipe_ingredient().contains(refrige.getIngredient().getIngredient_name())) {
                        if(index < 3) { score += 50.0; }
                        else if (refrige.getRefrige_ingredient_prior()) {
                            matchRatio += 2; // Give high match ratio weight
                            score += 20.0;
                        } else {
                            matchRatio += 1;
                            score += 10.0;
                        }
                    }
                    if (matchRatio != 0) score_percentage = (score / (150 + recipeIngredientLength)) * 100;
                    if (matchRatio != 0){
                        percentage = matchRatio / recipeIngredientLength * 100;
                    } else {
                        percentage = 0;
                    }
                    index += 1;
                }
            }
//            if (percentage != 0){
//                recommendations.add(new RecommendRes(recipe.getRecipe_id(), recipe.getRecipe_name(), recipe.getRecipe_amount(), recipe.getRecipe_content(),
//                        recipe.getRecipe_hit(), recipe.getRecipe_img(), recipe.getRecipe_level(), recipe.getRecipe_time(), percentage));
//            }
            if (score_percentage != 0.0) {
                recommendtest.add(new RecommendRes(recipe.getRecipe_id(), recipe.getRecipe_name(), recipe.getRecipe_amount(), recipe.getRecipe_content(),
                        recipe.getRecipe_hit(), recipe.getRecipe_img(), recipe.getRecipe_level(), recipe.getRecipe_time(), (int)score_percentage));
            }
        }

        // Sort recommendations in descending order of match ratio
//        recommendations.sort((r1, r2) -> Integer.compare(r2.getMatchRatio(), r1.getMatchRatio()));
        recommendtest.sort((r1, r2) -> Integer.compare(r2.getMatchRatio(), r1.getMatchRatio()));
//        return recommendations;
        return recommendtest;
    }

    @Override
    @CacheEvict(value = "recipes", allEntries = true)
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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

    @Override
    @Transactional(readOnly = true)
    public PageRes findIngredientsById(Long recipe_id) throws Exception {
        PageRes pageRes = new PageRes();

        try {
            List<RecipeIngredient> ingredients = recipeIngredientRepository.findIngredientsById(recipe_id);

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