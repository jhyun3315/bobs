package com.b304.bobs.api.service.Study;

import com.b304.bobs.api.response.Community.CommunityRes;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.request.Study.StudyReq;
import com.b304.bobs.api.response.Study.StudyPageRes;
import com.b304.bobs.api.response.Study.StudyRes;
import com.b304.bobs.api.response.StudyMember.StudyMemberRes;
import com.b304.bobs.db.entity.Study;
import com.b304.bobs.db.entity.StudyMember;
import com.b304.bobs.db.repository.StudyMemberRepository;
import com.b304.bobs.db.repository.StudyRepository;
import com.b304.bobs.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StudyServiceImpl implements StudyService {

    private final StudyRepository studyRepository;
    private final UserRepository userRepository;
    private final StudyMemberRepository studyMemberRepository;

    @Override
    public ModifyRes lockStudy(StudyReq studyReq) throws Exception {
        ModifyRes modifyRes = new ModifyRes();

        try {
            int result = studyRepository.lockStudy(studyReq.getUser_id());

            modifyRes.setResult(result);
            modifyRes.setId(studyReq.getUser_id());
            return modifyRes;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return modifyRes;
    }

    @Override
    public StudyRes createStudy(StudyReq studyReq) throws Exception {
        Study study = new Study();
        StudyRes result = new StudyRes();
        Long study_id = studyReq.getUser_id();

        System.out.println("여보세요나야~"+studyReq.getStudy_content());

        try {
            //사용자가 존재하는지
            if (userRepository.findById(study_id).isEmpty()) return result;

            System.out.println("읭");
            study.setStudy_content(studyReq.getStudy_content());
            study.setStudy_created(LocalDateTime.now());
            study.setStudy_time(studyReq.getStudy_time());
            study.setStudy_deleted(false);
            study.setStudy_lock(false);
            study.setStudy_onair(false);
            study.setStudy_title(studyReq.getStudy_title());
            study.setUser(userRepository.findById(studyReq.getUser_id()).orElse(null));

            result = new StudyRes(studyRepository.save(study));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public StudyPageRes modifyStudy(StudyReq studyReq) throws Exception {
        StudyPageRes studyPageRes = new StudyPageRes();

        try {
            int result = studyRepository.modifyStudy(
                    studyReq.getStudy_id(),
                    studyReq.getStudy_title(),
                    studyReq.getStudy_content(),
                    studyReq.getStudy_time());

            if(result==1) {
                studyPageRes.setResult(result);
                studyPageRes.setStudyRes(new StudyRes(studyRepository.findOneById(studyReq.getStudy_id())));

                List<StudyMember> lst = studyMemberRepository.findAllbyStudyId(studyReq.getStudy_id());
                studyPageRes.setMember_count(lst.size());

                studyPageRes.setStudyMemberResList(
                        lst.stream()
                        .map(StudyMemberRes::new)
                        .collect(Collectors.toList())
                );

                System.out.println("멤버는? :"+studyPageRes.getMember_count());

            }
            return studyPageRes;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return studyPageRes;
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
}
