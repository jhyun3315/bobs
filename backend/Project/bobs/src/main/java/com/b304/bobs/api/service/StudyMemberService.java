package com.b304.bobs.api.service;

import com.b304.bobs.api.request.StudyMemberReq;
import com.b304.bobs.api.response.ModifyRes;
import org.springframework.stereotype.Service;

@Service
public interface StudyMemberService {

    // 구성원 수 조회
    Long countMember(Long studyId) throws Exception;

    // 팀원이 스스로 탈퇴
    ModifyRes deleteStudyMember(Long studyMemberId) throws Exception;

}