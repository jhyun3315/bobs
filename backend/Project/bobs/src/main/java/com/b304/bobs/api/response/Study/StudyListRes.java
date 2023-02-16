package com.b304.bobs.api.response.Study;

import com.b304.bobs.api.response.StudyMember.StudyMemberInfoRes;
import com.b304.bobs.db.entity.Study;
import com.b304.bobs.db.entity.StudyMember;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StudyListRes {
    private Long study_id;
    private String study_title;
    private String study_content;
    private String study_time;
    private int member_count;
    private List<StudyMemberInfoRes> members;

    public StudyListRes() {
    }

    public StudyListRes(Study study, List<StudyMember> members) {
        List<StudyMemberInfoRes> member_list = new ArrayList<>();

        member_list = (members.stream()
                .map(StudyMemberInfoRes::new)
                .collect(Collectors.toList())
        );

        this.study_title = study.getStudy_title();
        this.study_content = study.getStudy_content();
        this.study_time = study.getStudy_time();
        this.study_id = study.getStudy_id();
        this.members = member_list;
        this.member_count = members.size();
    }

    public StudyListRes(Study study) {
        this.study_title = study.getStudy_title();
        this.study_content = study.getStudy_content();
        this.study_time = study.getStudy_time();
        this.study_id = study.getStudy_id();
    }
}
