package com.b304.bobs.api.service.StudyMember;

import com.b304.bobs.api.request.StudyMember.StudyMemberReq;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.response.Study.StudyInfoRes;
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

    // 스터디 가입
    @Override
    public StudyMemberRes createStudyMember(StudyMemberReq studyMemberReq) throws Exception {

        StudyMember studyMember = new StudyMember();
        StudyMemberRes result = new StudyMemberRes();

        Long user_id = studyMemberReq.getUser_id();
        Long study_id = studyMemberReq.getStudy_id();

        // 없는 사용자일 경우 false
        if (userRepository.isUserExist(user_id).isEmpty()) return result;
        // 현재 가입된 스터디가 3개 이상 false
        if(studyMemberRepository.findByUserId(user_id).size() >= 3) return result;

        try {
            Study study = studyRepository.findOneById(study_id);
            int member_count = studyMemberRepository.countMember(study_id).intValue();
            // 가입된 사용자가 아니거나 현재 4인이거나 스터디가 잠겨있을때 false
            if (userRepository.isUserExist(user_id).isEmpty() || member_count > 3 || study.getStudy_lock()) return result;

            // 이미 스터디의 구성원인 경우 false
            List<StudyMember> members = studyMemberRepository.findAllbyStudyId(study_id);
            for (StudyMember member : members) {
                if (member.getUser().getUser_id().equals(user_id)) return result;
            }

            // 3인 이하인 경우
            studyMember.setUser(userRepository.findById(studyMemberReq.getUser_id()).orElse(null));
            studyMember.setStudy(studyRepository.findById(studyMemberReq.getStudy_id()).orElse(null));
            studyMember.setStudy_member_deleted(false);
            result = new StudyMemberRes(studyMemberRepository.save(studyMember));

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
    public ModifyRes deleteStudyMember(StudyMemberReq studyMemberReq) throws Exception {

        ModifyRes modifyRes = new ModifyRes();
        Long user_id = studyMemberReq.getUser_id();
        Long study_id = studyMemberReq.getStudy_id();

        try {
            modifyRes.setId(study_id);
            // 해당 스터디의 구성원이 아닌경우
            if(studyMemberRepository.checkStudyMember(study_id, user_id).isEmpty()){
                modifyRes.setResult(0);
                return modifyRes;
            }

            int result = studyMemberRepository.deleteStudyMember(study_id, user_id);
            StudyMember studyMember = studyMemberRepository.findOne(study_id, user_id);

            System.out.println("탈퇴 결과:" + result);
            System.out.println("탈퇴하려는 사람의 역할:" + studyMember.isStudy_member_role());

            // 방장이 스터디 탈퇴를 한 경우
            if(result ==1 && studyMember.isStudy_member_role()){
                //1. 해당 스터디의 멤버들을 다 제거
                List<StudyMember> members = studyMemberRepository.findAllbyStudyId(study_id);

                for(StudyMember member: members){
                    Long memberUserId = member.getUser().getUser_id();
                    studyMemberRepository.deleteStudyMember(study_id, memberUserId);
                }

                //2 해당 스터디 deleted 설정
                int studyDeleted = studyRepository.deleteStudyById(study_id);
                if(studyDeleted==0) result =0;

            }
            modifyRes.setResult(result);

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