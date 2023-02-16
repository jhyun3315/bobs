package com.b304.bobs.api.service.Study;

import com.b304.bobs.api.request.Study.StudyMeetReq;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.request.Study.StudyReq;
import com.b304.bobs.api.response.Study.StudyMeetRes;
import com.b304.bobs.api.response.Study.StudyModifyRes;
import com.b304.bobs.api.response.Study.StudyRes;
import com.b304.bobs.db.entity.Study;
import com.b304.bobs.db.entity.StudyMember;
import com.b304.bobs.db.entity.User;
import com.b304.bobs.db.repository.StudyMemberRepository;
import com.b304.bobs.db.repository.StudyRepository;
import com.b304.bobs.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StudyServiceImpl implements StudyService {

    private final StudyRepository studyRepository;
    private final UserRepository userRepository;
    private final StudyMemberRepository studyMemberRepository;

    @Override
    public ModifyRes lockStudy(Long study_id) throws Exception {
        ModifyRes modifyRes = new ModifyRes();

        try {
            int result = studyRepository.lockStudy(study_id);

            modifyRes.setResult(result);
            modifyRes.setId(study_id);
            return modifyRes;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return modifyRes;
    }

    @Override
    public StudyRes createStudy(StudyReq studyReq) throws Exception {
        Study study = new Study();
        StudyMember studyMember = new StudyMember();

        StudyRes result = new StudyRes();
        Long study_id = studyReq.getUser_id();
        Long user_id = studyReq.getUser_id();

        try {
            //사용자가 존재하는지, 가입한 곳이 3개 이상이면 더이상 생성 x
            if (userRepository.isUserExist(user_id).isEmpty()) return result;
            if(studyMemberRepository.findByUserId(user_id).size() >= 3) return result;

            study.setStudy_content(studyReq.getStudy_content());
            study.setStudy_created(LocalDateTime.now());
            study.setStudy_time(studyReq.getStudy_time());
            study.setStudy_deleted(false);
            study.setStudy_lock(false);
            study.setStudy_onair(false);
            study.setStudy_title(studyReq.getStudy_title());
            study.setUser(userRepository.findById(studyReq.getUser_id()).orElse(null));

            result = new StudyRes(studyRepository.save(study));

            studyMember.setStudy(study);
            studyMember.setStudy_member_id(studyReq.getUser_id());
            studyMember.setStudy_member_role(true);
            studyMember.setUser(study.getUser());
            studyMember.setStudy_member_deleted(false);

            studyMemberRepository.save(studyMember);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public StudyModifyRes modifyStudy(StudyReq studyReq) throws Exception {
        StudyModifyRes studyModifyRes = new StudyModifyRes();

        try {
            int result = studyRepository.modifyStudy(
                    studyReq.getStudy_id(),
                    studyReq.getStudy_title(),
                    studyReq.getStudy_content(),
                    studyReq.getStudy_time());

            System.out.println(result);

            if(result==1) {
                User user = studyRepository.findOneById(studyReq.getStudy_id()).getUser();
                studyModifyRes = new StudyModifyRes(studyReq, user);
            }
            return studyModifyRes;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return studyModifyRes;
    }

    @Override
    public ModifyRes deleteStudy(Long study_id) throws Exception {
        ModifyRes modifyRes = new ModifyRes();

        try {
            int result = studyRepository.deleteStudyById(study_id);
            modifyRes.setResult(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modifyRes;
    }

    @Override
    @Transactional(readOnly = true)
    public StudyReq findOneById(Long study_id) throws Exception {
        StudyReq studyReq = new StudyReq();
        try {
            Study study = studyRepository.findOneById(study_id);

            if (study == null) return studyReq;
            else return new StudyReq(study);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return studyReq;
    }

    @Override
    @Transactional(readOnly = true)
    public PageRes findAll(Pageable pageable, Long user_id) throws Exception {

        PageRes pageRes = new PageRes();

        try {
            Page<Study> studies = studyRepository.findExcepJoined(user_id,pageable);
            System.out.println("페이지 몇개?"+studies.getTotalPages());
            if (studies.isEmpty()) return pageRes;

            pageRes
                    .setContents(studies.stream()
                    .map(StudyReq::new)
                    .collect(Collectors.toList())
                    );

            pageRes.setTotalPages(studies.getTotalPages());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageRes;
    }

    @Override
    @Transactional(readOnly = true)
    public PageRes findFullAll(Pageable pageable) throws Exception {
        PageRes pageRes = new PageRes();

        try {
            Page<Study> studies = studyRepository.findFullAll(pageable);
            if (studies.isEmpty()) return pageRes;
            pageRes
                    .setContents(studies.stream()
                            .map(StudyReq::new)
                            .collect(Collectors.toList())
                    );
            pageRes.setTotalPages(studies.getTotalPages());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageRes;
    }

    @Override
    public StudyMeetRes studyOnair(StudyMeetReq studyMeetReq) throws Exception {
        StudyMeetRes studyMeetRes = new StudyMeetRes();
        Long user_id = studyMeetReq.getUser_id();
        Long study_id = studyMeetReq.getStudy_id();
        boolean study_onair = studyMeetReq.isStudy_onair();

        try {
            // 1. 방장인지, 일반 구성원인지 확인
            Study study = studyRepository.findOneById(study_id);
            User studyLeader = study.getUser();

            // 2. 방장이면 onair = true 및 study_id 반환하여 들어갈수 있도록 함.
            if (studyLeader.getUser_id().equals(user_id)){

               int onairUpdate  = studyRepository.updateOnair(study_id, (study_onair)? 1:0 );

                studyMeetRes.setStudy_id(study_id);
                studyMeetRes.setStudy_onair(study_onair);
                studyMeetRes.setResult(onairUpdate == 1);

                return studyMeetRes;
            }
            // 3. 스터디 일반 구성원이라면, onair가 true일 때만 study_id 반환.
            if(studyMemberRepository.checkStudyMember(study_id, user_id).isPresent()){
                // 현재 onair 상태
                if(study.isStudy_onair()){
                    studyMeetRes.setStudy_onair(true);
                    studyMeetRes.setStudy_id(study_id);

                }else{
                    studyMeetRes.setStudy_onair(false);
                    studyMeetRes.setStudy_id(study_id);
                }
            }

            return studyMeetRes;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return studyMeetRes;
    }
}
