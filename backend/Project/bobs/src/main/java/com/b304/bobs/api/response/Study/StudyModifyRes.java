package com.b304.bobs.api.response.Study;

import com.b304.bobs.db.entity.Study;
import com.b304.bobs.db.entity.User;
import lombok.Getter;

@Getter
public class StudyModifyRes {
    private Long study_id;
    private String user_name;
    private String user_img;
    private String study_title;
    private String study_content;

    public StudyModifyRes() {
    }

    public StudyModifyRes(Study study) {
        User user = study.getUser();
        this.study_id = study.getStudy_id();
        this.user_name = user.getUser_name();
        this.user_img = user.getUser_profile();
        this.study_title = study.getStudy_title();
        this.study_content = study.getStudy_content();
    }
}
