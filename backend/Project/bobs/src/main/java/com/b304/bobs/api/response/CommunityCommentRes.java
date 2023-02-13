package com.b304.bobs.api.response;

import com.b304.bobs.db.entity.CommunityComment;
import com.b304.bobs.db.entity.User;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Getter
public class CommunityCommentRes {
    private Long user_id;
    private Long community_id;
    private Long community_comment_id;
    private String user_profile;
    private String user_name;
    private String community_comment_content;
    private LocalDateTime community_comment_created;

    public CommunityCommentRes() {

    }

    public CommunityCommentRes(CommunityComment communityComment) {
        User user = communityComment.getUser();
        this.user_id = user.getUser_id();
        this.community_comment_id = communityComment.getCommunity_comment_id();
        this.user_name = user.getUser_name();
        this.community_comment_content = communityComment.getCommunity_comment_content();
        this.community_comment_created = communityComment.getCommunity_comment_created();
        this.community_id = communityComment.getCommunity().getCommunity_id();
        this.user_profile = communityComment.getUser().getUser_profile();
    }

}
