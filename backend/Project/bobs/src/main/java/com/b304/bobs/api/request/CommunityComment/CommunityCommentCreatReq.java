package com.b304.bobs.api.request.CommunityComment;

import lombok.Getter;

@Getter
public class CommunityCommentCreatReq {
    private Long user_id;
    private Long community_id;
    private String community_comment_content;

    public CommunityCommentCreatReq() {
    }
}
