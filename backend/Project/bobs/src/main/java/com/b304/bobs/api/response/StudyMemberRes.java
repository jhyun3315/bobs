package com.b304.bobs.api.response;

import com.b304.bobs.db.entity.StudyMember;
import com.b304.bobs.db.entity.User;
import lombok.Getter;

@Getter
public class StudyMemberRes {
    private Long user_id;
    private String user_name;

    public StudyMemberRes() {

    }

    public StudyMemberRes(StudyMember studyMember) {
        User user = studyMember.getUser();
        this.user_id = user.getUser_id();
        this.user_name = user.getUser_name();
    }
}
