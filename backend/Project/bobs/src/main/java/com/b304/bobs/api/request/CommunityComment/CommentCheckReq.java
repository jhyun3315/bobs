package com.b304.bobs.api.request.CommunityComment;

import lombok.Getter;

@Getter
public class CommentCheckReq {
    private Long user_id;
    private Long community_comment_id;

    public CommentCheckReq() {

    }

    public CommentCheckReq(Long user_id, Long community_comment_id) {
        this.user_id = user_id;
        this.community_comment_id = community_comment_id;
    }
    public CommentCheckReq(CommunityCommentModiReq communityCommentModiReq) {
        this.community_comment_id = communityCommentModiReq.getCommunity_comment_id();
        this.user_id = communityCommentModiReq.getUser_id();

    }

}
