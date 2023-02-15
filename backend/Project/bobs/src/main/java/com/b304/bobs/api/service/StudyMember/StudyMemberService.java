package com.b304.bobs.api.service.StudyMember;

import com.b304.bobs.api.request.StudyMember.StudyMemberReq;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.response.Study.StudyInfoRes;
import com.b304.bobs.api.response.StudyMember.StudyMemberRes;
import org.springframework.stereotype.Service;

@Service
public interface StudyMemberService {

    // 가입
    StudyMemberRes createStudyMember(StudyMemberReq studyMemberReq) throws Exception;

    // 구성원 수 조회
    Long countMember(Long studyId) throws Exception;

    // 팀원이 스스로 탈퇴
    ModifyRes deleteStudyMember(Long studyMemberId) throws Exception;

    PageRes findAllByUser(Long user_id) throws  Exception;

    StudyInfoRes findOneById(StudyMemberReq studyMemberReq) throws Exception;
}