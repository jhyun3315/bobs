package com.b304.bobs.api.service.Study;

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

        try {
            //사용자가 존재하는지
            if (userRepository.findById(study_id).isEmpty()) return result;

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
    public PageRes findAll(Pageable pageable) throws Exception {

        PageRes pageRes = new PageRes();

        try {
            Page<Study> studies = studyRepository.findAll(pageable);
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
    public StudyMeetRes studyOnair(Long study_id, boolean study_onair) throws Exception {
        StudyMeetRes studyMeetRes = new StudyMeetRes();

//        try {
//            int result = studyRepository.studyOnair(study_id);
//
//            // 어쨌든 바뀜
//            if(result==1)
//            return modifyRes;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return studyMeetRes;
    }
}
