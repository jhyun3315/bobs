package com.b304.bobs.api.request.Study;

import lombok.Getter;

@Getter
public class StudyUserPageReq {
    private Long user_id;
    private Long study_id;

    public StudyUserPageReq() {
    }
}
