package com.b304.bobs.api.request.CommunityComment;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommunityCommentModiReq {
    private Long user_id;
    private Long community_comment_id;
    private String community_comment_content;

    public CommunityCommentModiReq() {
    }


}
