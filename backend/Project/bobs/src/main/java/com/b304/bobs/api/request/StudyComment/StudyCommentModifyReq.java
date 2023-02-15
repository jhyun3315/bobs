package com.b304.bobs.api.request.StudyComment;


import lombok.Getter;

@Getter
public class StudyCommentModifyReq {
    private Long user_id;
    private Long study_id;
    private Long study_comment_id;
    private String study_comment_content;

    public StudyCommentModifyReq() {
    }
}
