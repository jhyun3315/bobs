package com.b304.bobs.api.request;

import lombok.Getter;

@Getter
public class PageReq {
    private Long user_id;
    private Long community_id;
    private int page;
    private int size;

    public int getPage() {
        return page-1;
    }

    public int pageSizeForCommunity(){
        return 20;
    }

    public int pageSizeForComment(){
        return 5;
    }

}
