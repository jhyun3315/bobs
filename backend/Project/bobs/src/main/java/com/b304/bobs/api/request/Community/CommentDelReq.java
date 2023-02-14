package com.b304.bobs.api.request.Community;

import lombok.Getter;

@Getter
public class CommentDelReq {
    private Long user_id;
    private Long community_comment_id;

    public CommentDelReq() {

    }

    public CommentDelReq(Long user_id, Long community_comment_id) {
        this.user_id = user_id;
        this.community_comment_id = community_comment_id;
    }
}
