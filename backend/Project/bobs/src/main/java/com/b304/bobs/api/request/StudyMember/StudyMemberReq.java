package com.b304.bobs.api.request.StudyMember;

import com.b304.bobs.db.entity.StudyMember;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Data
public class StudyMemberReq {
    private Long study_member_id;
    private Boolean study_member_deleted;
    private Long study_id;
    private Long user_id;

    public StudyMemberReq() {

    }

    public StudyMemberReq(StudyMember studyMember) {
        this.study_member_id = studyMember.getStudy_member_id();
        this.study_member_deleted = studyMember.getStudy_member_deleted();
        this.study_id = studyMember.getStudy().getStudy_id();
        this.user_id = studyMember.getUser().getUser_id();
    }
}
