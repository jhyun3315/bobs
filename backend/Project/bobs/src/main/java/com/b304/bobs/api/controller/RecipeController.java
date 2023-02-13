package com.b304.bobs.api.controller;

import com.b304.bobs.api.request.PageReq;
import com.b304.bobs.api.request.RecipeUserLikeReq;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.response.RecipeRes;
import com.b304.bobs.api.response.RecipeStepRes;
import com.b304.bobs.api.service.RecipeService;
import com.b304.bobs.api.service.RecipeStepService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    final private RecipeService recipeService;
    final private RecipeStepService recipeStepService;

    @PutMapping("/{recipeId}/like")
    public ResponseEntity<Map<String, Object>> likeRecipe(@PathVariable Long recipeId, @RequestParam Long userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
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
