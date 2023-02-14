package com.b304.bobs.api.response.CommunityComment;

import com.b304.bobs.api.request.CommunityComment.CommunityCommentModiReq;
import com.b304.bobs.db.entity.CommunityComment;
import com.b304.bobs.db.entity.User;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CommunityCommentRes {
    private Long user_id;
    private Long community_comment_id;
    private String user_profile;
    private String user_name;
    private String community_comment_content;

    public CommunityCommentRes() {

    }

    public CommunityCommentRes(CommunityComment communityComment) {
        User user = communityComment.getUser();
        this.user_id = user.getUser_id();
        this.community_comment_id = communityComment.getCommunity_comment_id();
        this.user_name = user.getUser_name();
        this.user_profile = user.getUser_profile();
        this.community_comment_content = communityComment.getCommunity_comment_content();
    }

    public CommunityCommentRes(CommunityComment communityComment, CommunityCommentModiReq communityCommentModiReq) {
        User user = communityComment.getUser();
        this.user_id = user.getUser_id();
        this.community_comment_id = communityCommentModiReq.getCommunity_comment_id();
        this.user_name = user.getUser_name();
        this.user_profile = user.getUser_profile();
        this.community_comment_content = communityCommentModiReq.getCommunity_comment_content();
    }

}
