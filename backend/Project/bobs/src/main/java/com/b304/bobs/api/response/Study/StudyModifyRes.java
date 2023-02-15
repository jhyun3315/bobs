package com.b304.bobs.api.response.Study;

import com.b304.bobs.api.request.Study.StudyReq;
import com.b304.bobs.db.entity.User;
import lombok.Getter;

@Getter
public class StudyModifyRes {
    private Long study_id;
    private String user_name;
    private String user_profile;
    private String study_title;
    private String study_content;
    private String study_time;

    public StudyModifyRes() {
    }

    public StudyModifyRes(StudyReq studyReq, User user) {

        this.study_id = studyReq.getStudy_id();
        this.user_name = user.getUser_name();
        this.user_profile = user.getUser_profile();
        this.study_title = studyReq.getStudy_title();
        this.study_content = studyReq.getStudy_content();
        this.study_time = studyReq.getStudy_time();
    }
}
