package com.b304.bobs.api.request;

import lombok.Getter;

@Getter
public class RecipeUserLikeReq {
    private Long user_id;
    private int page;
    private final int page_size = 20;

    public RecipeUserLikeReq() {
    }

    public RecipeUserLikeReq(Long user_id, int page) {
        this.user_id = user_id;
        this.page = page-1;
    }
}
