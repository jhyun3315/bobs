package com.b304.bobs.api.response;

import com.b304.bobs.db.entity.Study;
import com.b304.bobs.db.entity.StudyMember;
import com.b304.bobs.db.entity.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class StudyRes {
    //leader
    private Long user_id;
    private String user_name;
    private String user_img;
    private String study_title;
    private String study_content;
    private List<StudyMember> member_list;
    private int member_count;

    public StudyRes() {
    }

    public StudyRes(Study study) {
        User user = study.getUser();
        List<StudyMember> members = new ArrayList<>();

        this.user_id = user.getUser_id();
        this.user_name = user.getUser_name();
        this.user_img = user.getUser_profile();
        this.study_title = study.getStudy_title();
        this.study_content = study.getStudy_content();
        this.member_list = study.getStudy_members();
        this.member_count = 1;
    }
}
