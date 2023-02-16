package com.b304.bobs.api.controller;

import com.b304.bobs.api.request.RecipeUserLike.RecipeUserLikeReq;
import com.b304.bobs.api.request.Recommend.RecommendReq;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.response.Recipe.RecipeRes;
import com.b304.bobs.api.response.RecipeStep.RecipeStepRes;
import com.b304.bobs.api.response.RecommendRes;
import com.b304.bobs.api.service.Recipe.RecipeService;
import com.b304.bobs.api.service.RecipeStep.RecipeStepService;
import com.b304.bobs.api.service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;
    private final RecipeStepService recipeStepService;
    private final UserService userService;

    @PostMapping("/recommendations")
    public ResponseEntity<Map<String, Object>> getRecommendationsByUserId(@RequestBody RecommendReq recommendReq) {
        Map<String, Object> map = new HashMap<String, Object>();

        List<RecommendRes> recommendations = recipeService.getRecommendedRecipesByUser(recommendReq);
        if (recommendations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        map.put("result",true);
        map.put("data", recommendations);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @PutMapping("/{recipeId}/like")
    public ResponseEntity<Map<String, Object>> likeRecipe(@PathVariable Long recipeId, @RequestParam Long userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {

            if(!userService.isUserExist(userId)){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }

            recipeService.recipeLike(userId, recipeId);
            map.put("result", true);
            return ResponseEntity.status(HttpStatus.OK).body(map);
        } catch (Exception e){
            e.printStackTrace();
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getALL() {
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            PageRes result = recipeService.findAll();

            if (result.getContents() == null) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            } else {
                map.put("data", result.getContents());
                map.put("result", true);
                return ResponseEntity.status(HttpStatus.OK).body(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity<?> getOne(@PathVariable("recipeId") Long recipe_id){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            RecipeRes result = recipeService.findOneById(recipe_id);

            if (result.getRecipe_id()==null) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
            else{
                map.put("data", result);
                map.put("result", true);
                return ResponseEntity.status(HttpStatus.OK).body(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @PostMapping("/likes")
    public ResponseEntity<Map<String, Object>> getRecipeUserLike(@RequestBody RecipeUserLikeReq recipeUserLikeReq){
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            if(!userService.isUserExist(recipeUserLikeReq.getUser_id())){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }

            PageRes result = recipeService.findByUserLike(recipeUserLikeReq.getUser_id());

            if (result.getContents()==null) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
            else{
                map.put("data", result);
                map.put("result", true);
                return ResponseEntity.status(HttpStatus.OK).body(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @GetMapping("/step/{recipeId}")
    public ResponseEntity<Map<String, Object>> getRecipeStep(@PathVariable("recipeId") Long recipe_id){
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            List<RecipeStepRes> result = recipeStepService.findById(recipe_id);

            if (result.isEmpty()) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
            else{
                map.put("data", result);
                map.put("result", true);
                return ResponseEntity.status(HttpStatus.OK).body(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @GetMapping("/ingredients/{recipe_id}")
    public ResponseEntity<?> getIngredients(@PathVariable("recipe_id") Long recipe_id){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            PageRes result = recipeService.findIngredientsById(recipe_id);

            if (result.getContents()==null) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
            else{
                map.put("data", result.getContents());
                map.put("result", true);
                return ResponseEntity.status(HttpStatus.OK).body(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

}
