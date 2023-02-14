package com.b304.bobs.api.response.StudyMember;

import com.b304.bobs.db.entity.StudyMember;
import lombok.Getter;

@Getter
public class StudyMemberRes {
    private Long study_member_id;
    private Long study_id;

    public StudyMemberRes() {

    }

    public StudyMemberRes(StudyMember studyMember) {
        this.study_member_id = studyMember.getStudy_member_id();
        this.study_id = studyMember.getStudy().getStudy_id();
     }
}
