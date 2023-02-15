package com.b304.bobs.api.response.StudyMember;

import com.b304.bobs.db.entity.StudyMember;
import com.b304.bobs.db.entity.User;
import lombok.Getter;

@Getter
public class StudyMemberInfoRes {
    private Long user_id;
    private String user_name;
    private String user_profile;

    public StudyMemberInfoRes() {
    }

    public StudyMemberInfoRes(StudyMember studyMember) {
        User user = studyMember.getUser();
        this.user_id = user.getUser_id();
        this.user_name = user.getUser_name();
        this.user_profile = user.getUser_profile();
    }
}
