package com.b304.bobs.api.request.Study;

import lombok.Getter;

@Getter
public class StudyModifyReq {
    private Long user_id;
    private Long study_id;
    private String study_content;
    private String study_time;
    private String study_title;

    public StudyModifyReq() {

    }
}
