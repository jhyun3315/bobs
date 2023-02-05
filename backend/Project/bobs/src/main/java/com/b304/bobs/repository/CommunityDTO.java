package com.b304.bobs.repository;

import com.b304.bobs.entity.Community;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CommunityDTO {
    private Long user_id;
    private Long community_id;
    private String community_title;
    private String community_content;
    private MultipartFile community_img;

    public CommunityDTO(Community community) {
        user_id = community.getUser().getUser_id();
        community_id = community.getCommunity_id();
        community_title = community.getCommunity_title();
        community_content = community.getCommunity_content();
        community_img = community.getCommunity_img();
    }
}
