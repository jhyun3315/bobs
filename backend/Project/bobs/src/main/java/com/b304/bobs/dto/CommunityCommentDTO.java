package com.b304.bobs.dto;

import com.b304.bobs.entity.Community;
import com.b304.bobs.entity.CommunityComment;
import com.b304.bobs.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Getter @Setter
public class CommunityCommentDTO {
    @NotBlank(message = "id는 공백일 수 없습니다.", groups = User.class)
    private Long community_comment_id;
    @NotBlank(message = "id는 공백일 수 없습니다.", groups = User.class)
    private String community_comment_content;
    @NotBlank(message = "id는 공백일 수 없습니다.", groups = User.class)
    private LocalDateTime community_comment_created;
    @NotBlank(message = "id는 공백일 수 없습니다.", groups = User.class)
    private boolean community_comment_deleted;
    @NotBlank(message = "id는 공백일 수 없습니다.", groups = User.class)
    private Long community_id;

    public CommunityCommentDTO(CommunityComment communityComment) {
        this.community_comment_id = communityComment.getCommunity_comment_id();
        this.community_comment_content = communityComment.getCommunity_comment_content();
        this.community_comment_created = communityComment.getCommunity_comment_created();
        this.community_comment_deleted = communityComment.getCommunity_comment_deleted();
        this.community_id = communityComment.getCommunity().getCommunity_id();
    }


}
