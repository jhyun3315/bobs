package com.b304.bobs.api.request;

import com.b304.bobs.db.entity.Study;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Data
public class StudyReq {
    private Long user_id;
    private Long study_id;
    private String study_content;
    private String study_time;
    private String study_title;

    public StudyReq() {
    }

    public StudyReq(Study study) {
        this.user_id = study.getUser().getUser_id();
        this.study_id = study.getStudy_id();
        this.study_content = study.getStudy_content();
        this.study_time = study.getStudy_time();
        this.study_title = study.getStudy_title();
    }
}
