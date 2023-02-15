package com.b304.bobs.api.service.Recommend;

import com.b304.bobs.db.entity.Allergy;
import com.b304.bobs.db.entity.Recipe;
import com.b304.bobs.db.entity.RecipeIngredient;
import com.b304.bobs.db.entity.Refrige;
import com.b304.bobs.db.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class RecommendServiceImpl implements RecommendService {

    private final UserRepository userRepository;
    private final AllergyRepository allergyRepository;
    private final RefrigeRepository refrigeRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final RecipeRepository recipeRepository;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void getRecommend(Long userId) {
        // Check if the match map is already in Redis
        String redisKey = "match:" + userId;
        String matchString = redisTemplate.opsForValue().get(redisKey);
        if (matchString != null) {
            Map<Integer, Integer> match = new HashMap<>();
            // Convert the match string to a map
            Pattern pattern = Pattern.compile("\\{(.*?)}");
            Matcher matcher = pattern.matcher(matchString);
            if (matcher.find()) {
                String[] pairs = matcher.group(1).split(", ");
                for (String pair : pairs) {
                    String[] keyValue = pair.split("=");
                    if (!keyValue[0].isEmpty() && !keyValue[1].isEmpty()) {
                        Integer key = Integer.parseInt(keyValue[0]);
                        Integer value = Integer.parseInt(keyValue[1]);
                        match.put(key, value);
                    }
                }
            }
            // Use the match map from Redis
            System.out.println(match);
            return;
        }

        List<RecipeIngredient> recipeIngredients = recipeIngredientRepository.findAll();
        List<Recipe> recipes = recipeRepository.findAll();

        List<Map<String, Object>> recipeList = new ArrayList<>();
        for (Recipe recipe : recipes) {
            List<RecipeIngredient> ingredients = new ArrayList<>();
            for (RecipeIngredient ingredient : recipeIngredients) {
                if (ingredient.getRecipe_ingredient_id() == recipe.getRecipe_id()) {
                    ingredients.add(ingredient);
                }
            }
            recipeList.add(Map.of("recipe_id", recipe.getRecipe_id(), "recipe_ingredients", ingredients));
        }

        List<Allergy> allergies = allergyRepository.findByUserId(userId);
        Set<Integer> allergyRecipes = new HashSet<>();

        List<Refrige> normalRefers = refrigeRepository.findNomalByUser(userId);
        List<Refrige> priorityRefers = refrigeRepository.findPriorByUser(userId);

        Map<Long, Integer> priorityCnt = new HashMap<>();
        for (Refrige refer : priorityRefers) {
            priorityCnt.put(refer.getRefrige_id(), 0);
        }

        Map<Integer, Integer> cnt = new HashMap<>();
        for (Refrige refer : normalRefers) {
            for (int j = 0; j < recipeList.size(); j++) {
                int recipeId = (int) recipeList.get(j).get("recipe_id");
                if (!cnt.containsKey(recipeId)) {
                    cnt.put(recipeId, 0);
                }
                if (allergyRecipes.contains(recipeId)) {
                    continue;
                }
                List<RecipeIngredient> ingredients = (List<RecipeIngredient>) recipeList.get(j).get("recipe_ingredients");
                for (int k = 0; k < ingredients.size(); k++) {
                    String referName = refer.getIngredient().getIngredient_name().split("\\(")[0];
                    if (ingredients.get(k).getRecipe_ingredient().contains(referName)) {
                        if (priorityCnt.containsKey(refer.getRefrige_id())) {
                            priorityCnt.put(refer.getRefrige_id(), priorityCnt.get(refer.getRefrige_id()) + 1);
                        }
                        if (k < 3) {
                            cnt.put(recipeId, cnt.get(recipeId) + 50);
                        } else {
                            cnt.put(recipeId, cnt.get(recipeId) + 10);
                        }
                    } else if (referName.equals("계란") && ingredients.get(k).getRecipe_ingredient().contains("달걀")) {
                        if (k < 3) {
                            cnt.put(recipeId, cnt.get(recipeId) + 50);
                        } else {
                            cnt.put(recipeId, cnt.get(recipeId) + 10);
                        }
                    }
                }
            }
        }

        for (Allergy allergy : allergies) {
            for (int j = 0; j < recipeList.size(); j++) {
                int recipeId = (int) recipeList.get(j).get("recipe_id");
                List<RecipeIngredient> ingredients = (List<RecipeIngredient>) recipeList.get(j).get("recipe_ingredients");
                for (RecipeIngredient ingredient : ingredients) {
                    if (ingredient.getRecipe_ingredient().equals(allergy.getIngredient().getIngredient_name())) {
                        allergyRecipes.add(recipeId);
                        break;
                    }
                }
            }
        }

        Map<Integer, Integer> match = new HashMap<>();
        for (int i = 0; i < recipeList.size(); i++) {
            boolean isAllergy = false;
            int maxNumItems = Collections.max(((List<RecipeIngredient>) recipeList.get(i).get("recipe_ingredients")), Comparator.comparing(RecipeIngredient::getRecipe_ingredient)).getRecipe_ingredient().length();
            int tmp = 150 + maxNumItems * 10;
            int score = cnt.get((int) recipeList.get(i).get("recipe_id"));
            double bonus = ((double) priorityCnt.getOrDefault((int) recipeList.get(i).get("recipe_id"), 0) / priorityRefers.size()) * 100;

            for (Allergy allergy : allergies) {
                for (RecipeIngredient ingredient : ((List<RecipeIngredient>) recipeList.get(i).get("recipe_ingredients"))) {
                    if (ingredient.getRecipe_ingredient().equals(allergy.getIngredient().getIngredient_name())) {
                        isAllergy = true;
                        break;
                    }
                }
                if (isAllergy) {
                    break;
                }
            }

            if (isAllergy) {
                match.put((int) recipeList.get(i).get("recipe_id"), 0);
            } else {
                double matchScore = (score + bonus) / tmp * 100;
                int matchPercent = (int) Math.max(0, Math.min(100, matchScore));
                match.put((int) recipeList.get(i).get("recipe_id"), matchPercent);
            }
        }

        // Store the match map in Redis
        redisKey = "match:" + userId;
        redisTemplate.opsForValue().set(redisKey, match.toString());

        System.out.println(match);

    }
}
