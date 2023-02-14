package com.b304.bobs.api.response.Community;

import com.b304.bobs.db.entity.Community;
import com.b304.bobs.db.entity.User;
import lombok.Getter;

@Getter
public class CommunityRes {
    private String user_name;
    private Long community_id;
    private String community_title;
    private String community_content;
    private int community_hit;
    private String community_img;

    public CommunityRes() {
    }

    public CommunityRes(Community community, String imgUrl) {
        User user = community.getUser();
        this.user_name = user.getUser_name();
        this.community_id = community.getCommunity_id();
        this.community_title = community.getCommunity_title();
        this.community_content = community.getCommunity_content();
        this.community_hit = community.getCommunity_hit();
        this.community_img = imgUrl;
    }

    public CommunityRes(Community community) {
        this.user_name = community.getUser().getUser_name();
        this.community_id = community.getCommunity_id();
        this.community_title = community.getCommunity_title();
        this.community_content = community.getCommunity_content();
        this.community_hit = community.getCommunity_hit();
        this.community_img = community.getCommunity_img();
    }


}
