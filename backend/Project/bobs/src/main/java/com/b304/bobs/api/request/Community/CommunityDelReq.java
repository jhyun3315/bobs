package com.b304.bobs.api.request.Community;

import lombok.Getter;

@Getter
public class CommunityDelReq {
    private Long user_id;
    private Long community_id;

    public CommunityDelReq() {
    }

}
