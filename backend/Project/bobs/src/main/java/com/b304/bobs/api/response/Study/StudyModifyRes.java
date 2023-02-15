package com.b304.bobs.api.response.Study;

import com.b304.bobs.api.request.Study.StudyModifyReq;
import com.b304.bobs.api.request.Study.StudyReq;
import com.b304.bobs.db.entity.Study;
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

    public StudyModifyRes(StudyModifyReq studyModifyReq, User user) {

        this.study_id = studyModifyReq.getStudy_id();
        this.user_name = user.getUser_name();
        this.user_profile = user.getUser_profile();
        this.study_title = studyModifyReq.getStudy_title();
        this.study_content = studyModifyReq.getStudy_content();
        this.study_time = studyModifyReq.getStudy_time();
    }
}
