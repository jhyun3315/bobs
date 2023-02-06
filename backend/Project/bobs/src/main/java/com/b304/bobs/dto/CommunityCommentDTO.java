package com.b304.bobs.dto;

import com.b304.bobs.entity.Community;
import com.b304.bobs.entity.CommunityComment;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter @Setter
public class CommunityCommentDTO {
    private Long community_comment_id;
    private String community_comment_content;
    private LocalDateTime community_comment_created;
    private boolean community_comment_deleted;
    private Long community_id;

    public CommunityCommentDTO(CommunityComment communityComment) {
        this.community_comment_id = communityComment.getCommunity_comment_id();
        this.community_comment_content = communityComment.getCommunity_comment_content();
        this.community_comment_created = communityComment.getCommunity_comment_created();
        this.community_comment_deleted = communityComment.getCommunity_comment_deleted();
        this.community_id = communityComment.getCommunity().getCommunity_id();
    }


}
