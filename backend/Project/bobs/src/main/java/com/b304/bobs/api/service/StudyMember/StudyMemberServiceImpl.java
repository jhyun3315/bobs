package com.b304.bobs.api.service.StudyMember;

import com.b304.bobs.api.request.StudyMember.StudyMemberReq;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.response.Study.StudyInfoRes;
import com.b304.bobs.api.response.Study.StudyRes;
import com.b304.bobs.api.response.Study.StudyUserPageRes;
import com.b304.bobs.api.response.StudyMember.StudyMemberInfoRes;
import com.b304.bobs.api.response.StudyMember.StudyMemberRes;
import com.b304.bobs.db.entity.Study;
import com.b304.bobs.db.entity.StudyMember;
import com.b304.bobs.db.repository.StudyMemberRepository;
import com.b304.bobs.db.repository.StudyRepository;
import com.b304.bobs.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        Long user_id = studyMemberReq.getUser_id();
        Long study_id = studyMemberReq.getStudy_id();

        if (userRepository.isUserExist(user_id).isEmpty()) return result;

        try {
            if (studyRepository.findOneById(study_id).getStudy_id().equals(study_id)
                    &&  studyMemberRepository.countMember(study_id) <= 3) {

                studyMember.setUser(userRepository.findById(studyMemberReq.getUser_id()).orElse(null));
                studyMember.setStudy(studyRepository.findById(studyMemberReq.getStudy_id()).orElse(null));
                studyMember.setStudy_member_deleted(false);

                result = new StudyMemberRes(studyMemberRepository.save(studyMember));
            } else return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Long countMember(Long study_member_id) throws Exception {
        Long count = 0L;
        try {
            count = studyMemberRepository.countMember(study_member_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
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

    @Override
    @Transactional(readOnly = true)
    public PageRes findAllByUser(Long user_id) throws Exception {
        PageRes pageRes = new PageRes();
        try {
            List<StudyUserPageRes> studyUserPageResList = new ArrayList<>();
            List<StudyMember> members = studyMemberRepository.findByUserId(user_id);
            if (members.isEmpty()) {
                return pageRes;
            }

            for (StudyMember member : members) {
                Study study = member.getStudy();
                List<StudyMember> lst = studyMemberRepository.findAllbyStudyId(study.getStudy_id());

                StudyUserPageRes studyUserPageRes = new StudyUserPageRes(study, lst.size());
                studyUserPageResList.add(studyUserPageRes);
            }
            pageRes.setContents(studyUserPageResList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageRes;
    }

    @Override
    @Transactional(readOnly = true)
    public StudyInfoRes findOneById(StudyMemberReq studyMemberReq) throws Exception {
        StudyInfoRes studyInfoRes = new StudyInfoRes();

        try {
            Long user_id = studyMemberReq.getUser_id();
            Long study_id = studyMemberReq.getStudy_id();

            List<StudyMember> members = studyMemberRepository.findOneByStudyId(study_id);
            if (members.isEmpty()) return studyInfoRes;

            Study study = studyRepository.findOneById(study_id);
            if(study.equals(new Study())) return studyInfoRes;

            List<StudyMemberInfoRes> member_list= new ArrayList<>();

            member_list = (members.stream()
                    .map(StudyMemberInfoRes::new)
                    .collect(Collectors.toList())
            );

            studyInfoRes = new StudyInfoRes(study, member_list,user_id);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return studyInfoRes;
    }
}