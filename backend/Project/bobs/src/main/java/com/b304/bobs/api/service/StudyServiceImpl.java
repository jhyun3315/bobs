package com.b304.bobs.api.service;

import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.request.StudyReq;
import com.b304.bobs.db.entity.Study;
import com.b304.bobs.db.repository.StudyRepository;
import com.b304.bobs.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudyServiceImpl implements StudyService {

    private final StudyRepository studyRepository;
    private final UserRepository userRepository;

    @Override
    public StudyReq createStudy(StudyReq studyReq) throws Exception {

        Study study = new Study();
        StudyReq result = new StudyReq();

        try {
            study.setStudy_content(studyReq.getStudy_content());
            study.setStudy_created(LocalDateTime.now());
            study.setStudy_deleted(false);
            study.setStudy_lock(false);
            study.setStudy_title(studyReq.getStudy_title());

            if (studyRepository.findById(studyReq.getUser_id()).isPresent()) {
                study.setUser(userRepository.findById(studyReq.getUser_id()).orElse(null));
                result = new StudyReq(studyRepository.save(study));
            }

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
                    studyReq.getStudy_id(),
                    studyReq.getStudy_title(),
                    studyReq.getStudy_content(),
                    studyReq.getStudy_time());

            modifyRes.setResult(result);
            modifyRes.setId(studyReq.getStudy_id());
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
