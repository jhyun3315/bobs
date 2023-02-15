package com.b304.bobs.api.response.StudyComment;

import com.b304.bobs.db.entity.StudyComment;
import com.b304.bobs.db.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter @Setter
public class StudyCommentRes {
    private Long study_comment_id;
    private Long user_id;
    private String user_name;
    private String user_profile;
    private String study_comment_content;

    public StudyCommentRes() {

    }

    public StudyCommentRes(StudyComment studyComment) {
        User user = studyComment.getUser();
        this.study_comment_id = studyComment.getStudy_comment_id();
        this.user_id = user.getUser_id();
        this.user_name = user.getUser_name();
        this.user_profile = user.getUser_profile();
        this.study_comment_content = studyComment.getStudy_comment_content();
    }
}
