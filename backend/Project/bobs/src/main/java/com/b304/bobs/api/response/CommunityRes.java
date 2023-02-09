package com.b304.bobs.api.response;

import com.b304.bobs.api.request.CommunityReq;
import com.b304.bobs.db.entity.Community;
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

    public CommunityRes(CommunityReq communityReq, String imgUrl) {
        this.user_name = communityReq.getUser_name();
        this.community_id = communityReq.getCommunity_id();
        this.community_title = communityReq.getCommunity_title();
        this.community_content = communityReq.getCommunity_content();
        this.community_hit = communityReq.getCommunity_hit();
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
