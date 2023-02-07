package com.b304.bobs.service;

import com.b304.bobs.dto.CommunityDTO;
import com.b304.bobs.dto.ModifyDTO;
import com.b304.bobs.dto.PageResultDTO;
import com.b304.bobs.dto.StudyDTO;
import com.b304.bobs.entity.Community;
import com.b304.bobs.entity.Study;
import com.b304.bobs.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudyServiceImpl implements StudyService {

    private final StudyRepository studyRepository;

    @Override
    public StudyDTO createStudy(StudyDTO studyDTO) throws Exception {

        Study study = new Study();
        StudyDTO result = new StudyDTO();

        try {
            study.setStudy_content(studyDTO.getStudy_content());
            study.setStudy_created(LocalDateTime.now());
            study.setStudy_deleted(false);
            study.setStudy_lock(false);
            study.setStudy_title(studyDTO.getStudy_title());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public ModifyDTO deleteStudy(Long study_id) throws Exception {
        return null;
    }

    @Override
    public ModifyDTO modifyStudy(StudyDTO studyDTO) throws Exception {
        return null;
    }

    @Override
    public StudyDTO findOneById(Long study_id) throws Exception {
        return null;
    }

    @Override
    public StudyDTO findAll(Pageable pageable) throws Exception {
        return null;
    }
}
