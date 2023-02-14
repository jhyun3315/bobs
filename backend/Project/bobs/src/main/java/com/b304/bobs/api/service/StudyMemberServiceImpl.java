package com.b304.bobs.api.service;

import com.b304.bobs.api.request.StudyMemberReq;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.StudyMemberRes;
import com.b304.bobs.db.entity.StudyMember;
import com.b304.bobs.db.repository.StudyMemberRepository;
import com.b304.bobs.db.repository.StudyRepository;
import com.b304.bobs.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StudyMemberServiceImpl implements StudyMemberService {

    private final StudyMemberRepository studyMemberRepository;

    private final UserRepository userRepository;

    private final StudyRepository studyRepository;

    @Override
    public StudyMemberRes createStudyMember(StudyMemberReq studyMemberReq) throws Exception {

        StudyMember studyMember = new StudyMember();
        StudyMemberRes result = new StudyMemberRes();
        Long study_member_id = studyMemberReq.getUser_id();

        if (userRepository.findById(study_member_id).isEmpty())
            return result;

        try {
            if (studyMemberRepository.findById(studyMemberReq.getUser_id()).isPresent()
            && countMember(studyMemberReq.getStudy_id()) <= 2) {

                studyMember.setUser(userRepository.findById(studyMemberReq.getUser_id()).orElse(null));
                studyMember.setStudy_member_deleted(studyMemberReq.getStudy_member_deleted());
                studyMember.setStudy(studyRepository.findById(studyMemberReq.getStudy_id()).orElse(null));

                result = new StudyMemberRes(studyMemberRepository.save(studyMember));

            } else return result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

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