package com.b304.bobs.api.request.CommunityComment;

import lombok.Getter;

@Getter
public class CommunityCommentGetReq {
    private Long user_id;
    private Long community_id;

    public CommunityCommentGetReq() {
    }


}
