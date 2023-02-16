package com.b304.bobs.api.response.Study;

import com.b304.bobs.db.entity.Study;
import lombok.Getter;

@Getter
public class StudyListRes {
    private String study_title;
    private String study_content;
    private Long study_id;
    private int member_count;

    public StudyListRes() {
    }

    public StudyListRes(Study study, int member_count) {
        this.study_title = study.getStudy_title();
        this.study_content = study.getStudy_content();
        this.study_id = study.getStudy_id();
        this.member_count = member_count;
    }

    public StudyListRes(Study study) {
        this.study_title = study.getStudy_title();
        this.study_content = study.getStudy_content();
        this.study_id = study.getStudy_id();
    }
}
