package com.b304.bobs.api.response.Community;

import com.b304.bobs.db.entity.Community;
import com.b304.bobs.db.entity.CommunityComment;
import com.b304.bobs.db.entity.User;
import lombok.Getter;

@Getter
public class CommunityRes {
    private String user_name;
    private boolean check_writer;
    private Long community_id;
    private String community_title;
    private String community_content;
    private int comment_count;
    private String community_img;

    public CommunityRes() {
    }

    public CommunityRes(Community community, String imgUrl, Long origin_user_id) {
        User user = community.getUser();
        this.check_writer = user.getUser_id().equals(origin_user_id);
        this.user_name = user.getUser_name();
        this.community_id = community.getCommunity_id();
        this.community_title = community.getCommunity_title();
        this.community_content = community.getCommunity_content();
        this.comment_count =  community.getCommunity_comments().size();
        this.community_img = imgUrl;
    }

    public CommunityRes(Community community, Long origin_user_id) {
        User user = community.getUser();
        this.user_name = user.getUser_name();
        this.check_writer = user.getUser_id().equals(origin_user_id);
        this.community_id = community.getCommunity_id();
        this.community_title = community.getCommunity_title();
        this.community_content = community.getCommunity_content();
        this.comment_count =  community.getCommunity_comments().size();
        this.community_img = community.getCommunity_img();
    }


    public CommunityRes(Community community) {
        User user = community.getUser();
        this.user_name = user.getUser_name();
        this.community_id = community.getCommunity_id();
        this.community_title = community.getCommunity_title();
        this.community_content = community.getCommunity_content();
        this.comment_count =  community.getCommunity_comments().size();
        this.community_img = community.getCommunity_img();
    }
}
