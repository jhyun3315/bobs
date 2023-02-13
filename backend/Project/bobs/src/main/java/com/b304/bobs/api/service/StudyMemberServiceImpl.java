package com.b304.bobs.api.service;

import com.b304.bobs.api.request.StudyMemberReq;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.db.entity.StudyMember;
import com.b304.bobs.db.repository.StudyMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StudyMemberServiceImpl implements StudyMemberService {

    private final StudyMemberRepository studyMemberRepository;


    @Override
    public Long countMember(Long study_member_id) throws Exception {

        StudyMemberReq studyMemberReq = new StudyMemberReq();
        try {
            Long result = studyMemberRepository.countMember(study_member_id);

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return study_member_id;
    }

    @Override
    public ModifyRes deleteStudyMember(Long studyMemberId) throws Exception {

        ModifyRes modifyRes = new ModifyRes();

        try {
            int result = studyMemberRepository.deleteStudyMember(studyMemberId);
            modifyRes.setResult(result);
            modifyRes.setId(studyMemberId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return modifyRes;
    }
}