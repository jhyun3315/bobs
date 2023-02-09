package com.b304.bobs.api.controller;

import com.b304.bobs.api.request.PageReq;
import com.b304.bobs.api.request.RecipeUserLikeReq;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.response.RecipeRes;
import com.b304.bobs.api.service.RecipeService;
import com.b304.bobs.api.service.RecipeStepService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("api/recipes")
public class RecipeController {

    final private RecipeService recipeService;
    final private RecipeStepService recipeStepService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getALl(@RequestParam(value="page") int page) {
        Map<String, Object> map = new HashMap<String, Object>();
        PageReq pageReq = new PageReq(page);
        PageRequest pageRequest = PageRequest.of(pageReq.getPage(), pageReq.pageSizeForCommunity(), Sort.by("recipe_hit").descending());

        try {
            PageRes result = recipeService.findAll(pageRequest);

            if (result.getContents() == null) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            } else {
                map.put("data", result.getContents());
                map.put("total_page", result.getTotalPages());
                map.put("current_page", page + 1);
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
    public ResponseEntity<?> getRecipeUserLike(@RequestBody RecipeUserLikeReq recipeUserLikeReq){
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println(recipeUserLikeReq.getPage()+" "+recipeUserLikeReq.getPage_size());

        PageRequest pageRequest = PageRequest.of(recipeUserLikeReq.getPage(),recipeUserLikeReq.getPage_size());

        try {
            PageRes result = recipeService.findByUserLike(recipeUserLikeReq.getUser_id(), pageRequest);

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
    public ResponseEntity<?> getRecipeStep(@PathVariable("recipeId") Long recipe_id){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            PageRes result = recipeStepService.findById(recipe_id);

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

}