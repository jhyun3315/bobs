package com.b304.bobs.api.request.Community;

import lombok.Getter;

@Getter
public class CommunityCheckReq {
    private Long user_id;
    private Long community_id;

    public CommunityCheckReq() {
    }

}
