package com.b304.bobs.api.response.Study;

import com.b304.bobs.db.entity.Study;
import lombok.Getter;


@Getter
public class StudyUserPageRes {
    private int member_count;
    private String study_title;
    private String study_time;
    private Long study_id;
    private boolean study_onair;

    public StudyUserPageRes() {

    }

    public StudyUserPageRes(Study study, int member_count) {
        this.member_count = member_count;
        this.study_id = study.getStudy_id();
        this.study_title = study.getStudy_title();
        this.study_time = study.getStudy_time();
        this.study_onair = isStudy_onair();
    }

}
