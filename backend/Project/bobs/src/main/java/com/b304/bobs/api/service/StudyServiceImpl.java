package com.b304.bobs.api.service;

import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.request.StudyReq;
import com.b304.bobs.api.response.StudyRes;
import com.b304.bobs.db.entity.Study;
import com.b304.bobs.db.entity.StudyMember;
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

    @Override
    public StudyRes createStudy(StudyReq studyReq) throws Exception {
        Study study = new Study();
        StudyRes result = new StudyRes();
        Long study_id = studyReq.getUser_id();

        if(userRepository.findById(study_id).isEmpty()) return result;

        try {
            //사용자가 존재하는지
            if (studyRepository.findById(studyReq.getUser_id()).isPresent()) {
                study.setStudy_content(studyReq.getStudy_content());
                study.setStudy_created(LocalDateTime.now());
                study.setStudy_time(studyReq.getStudy_time());
                study.setStudy_deleted(false);
                study.setStudy_lock(false);
                study.setStudy_title(studyReq.getStudy_title());
                study.setUser(userRepository.findById(studyReq.getUser_id()).orElse(null));

//                study.setStudy_members(study.addStudyMember());

                result = new StudyRes(studyRepository.save(study));

            }else return result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public ModifyRes modifyStudy(StudyReq studyReq) throws Exception {
        ModifyRes modifyRes = new ModifyRes();

        try {
            int result = studyRepository.modifyStudy(
                    studyReq.getUser_id(),
                    studyReq.getStudy_title(),
                    studyReq.getStudy_content(),
                    studyReq.getStudy_time());

            modifyRes.setResult(result);
            modifyRes.setId(studyReq.getUser_id());
            return modifyRes;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return modifyRes;
    }

    @Override
    public ModifyRes deleteStudy(Long study_id) throws Exception {
        ModifyRes modifyRes = new ModifyRes();

        try {
            int result = studyRepository.deleteStudyById(study_id);
            modifyRes.setResult(result);
            modifyRes.setId(study_id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return modifyRes;
    }

    @Override
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
}
