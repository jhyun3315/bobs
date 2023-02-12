package com.b304.bobs.api.request;

import lombok.Getter;
@Getter
public class PageReq {
    private Long user_id;
    private int page;
    private int size;

    public PageReq() {

    }

    public PageReq(int page) {
        this.page = page;
    }

    public int getPage() {
        return this.page-1;
    }


    public int pageSizeForCommunity(){
        return 20;
    }

    public int pageSizeForComment(){
        return 5;
    }

}
