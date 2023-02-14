package com.b304.bobs.api.service;

import com.b304.bobs.api.response.ModifyRes;

public interface StudyMemberService {
    // 팀원이 스스로 탈퇴
    public ModifyRes deleteStudyMember(Long studyMemberId) throws Exception;

}