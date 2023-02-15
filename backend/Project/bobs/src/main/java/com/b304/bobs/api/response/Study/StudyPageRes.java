package com.b304.bobs.api.response.Study;

import com.b304.bobs.api.response.StudyMember.StudyMemberRes;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class StudyPageRes {
    private int result;
    private int member_count;
    private List<StudyMemberRes> studyMemberResList;
    private StudyRes studyRes;

    public boolean getResult() {
        return this.result == 1;
    }

    public StudyPageRes() {
    }

}
