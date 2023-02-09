package com.b304.bobs.api.dto;

import com.b304.bobs.entity.Community;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Data
public class CommunityDTO {
    private Long user_id;
    private String user_name;
    private Long community_id;
    private String community_title;
    private String community_content;
    private int community_hit;
    private LocalDateTime community_created;
    private boolean community_deleted;
    private String community_img;

    public CommunityDTO() {

    }

    public CommunityDTO(Community community) {
        this.user_id = community.getUser().getUser_id();
        this.user_name = community.getUser().getUser_name();
        this.community_id = community.getCommunity_id();
        this.community_title = community.getCommunity_title();
        this.community_content = community.getCommunity_content();
        this.community_hit = community.getCommunity_hit();
        this.community_created = community.getCommunity_createdTime();
        this.community_deleted = community.getCommunity_deleted();
        this.community_img = community.getCommunity_img();
    }

    public boolean getCommunity_deleted() {
        return this.community_deleted;
    }
}
