package com.b304.bobs.dto;

import com.b304.bobs.entity.Community;
import com.b304.bobs.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@Getter @Setter
public class CommunityUpload {
    private Long user_id;
    private Long community_id;
    private String user_name;
    private String community_title;
    private String community_content;
    private MultipartFile community_img;
}
