package com.b304.bobs.api.request;

import lombok.Getter;

@Getter
public class StudyCommentDelReq {
    private Long user_id;
    private Long study_comment_id;

    public StudyCommentDelReq() {
    }
}
