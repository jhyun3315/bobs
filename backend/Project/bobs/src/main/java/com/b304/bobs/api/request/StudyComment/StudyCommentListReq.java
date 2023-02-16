package com.b304.bobs.api.request.StudyComment;

import lombok.Getter;

@Getter
public class StudyCommentListReq {
    private Long user_id;
    private Long study_id;

    public StudyCommentListReq() {
    }
}
