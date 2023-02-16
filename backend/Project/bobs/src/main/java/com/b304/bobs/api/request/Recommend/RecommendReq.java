package com.b304.bobs.api.request.Recommend;

import lombok.Getter;

import java.util.List;

@Getter
public class RecommendReq {
    private Long user_id;
    private List<String> selectedIngredients;

    public RecommendReq(Long user_id, List<String> selectedIngredients) {
        this.user_id = user_id;
        this.selectedIngredients = selectedIngredients;
    }
}
