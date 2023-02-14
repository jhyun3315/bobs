package com.b304.bobs.api.request;

import com.b304.bobs.db.entity.StudyComment;
import lombok.Getter;


@Getter
public class StudyCommentReq {
    private Long user_id;
    private Long study_id;
    private String study_comment_content;

    public StudyCommentReq() {
    }

    public StudyCommentReq(StudyComment studyComment) {
        this.user_id = studyComment.getUser().getUser_id();
        this.study_id = studyComment.getStudy().getStudy_id();
        this.study_comment_content = studyComment.getStudy_comment_content();
    }
}
