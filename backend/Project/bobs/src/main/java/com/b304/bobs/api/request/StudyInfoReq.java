package com.b304.bobs.api.request;

import lombok.Getter;

@Getter
public class StudyInfoReq {
    private Long study_id;
    private Long user_id;

    public StudyInfoReq() {
    }
}
