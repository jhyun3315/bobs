package com.b304.bobs.api.service;

import com.b304.bobs.api.dto.ModifyDTO;
import com.b304.bobs.api.dto.PageResultDTO;
import com.b304.bobs.api.dto.StudyDTO;
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
    public StudyDTO createStudy(StudyDTO studyDTO) throws Exception {

        Study study = new Study();
        StudyDTO result = new StudyDTO();

        try {
            study.setStudy_content(studyDTO.getStudy_content());
            study.setStudy_created(LocalDateTime.now());
            study.setStudy_deleted(false);
            study.setStudy_lock(false);
            study.setStudy_title(studyDTO.getStudy_title());

            if (studyRepository.findById(studyDTO.getUser_id()).isPresent()) {
                study.setUser(userRepository.findById(studyDTO.getUser_id()).orElse(null));
                result = new StudyDTO(studyRepository.save(study));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public ModifyDTO modifyStudy(StudyDTO studyDTO) throws Exception {
        ModifyDTO modifyDTO = new ModifyDTO();

        try {
            int result = studyRepository.modifyStudy(
                    studyDTO.getStudy_id(),
                    studyDTO.getStudy_title(),
                    studyDTO.getStudy_content(),
                    studyDTO.getStudy_time());

            modifyDTO.setResult(result);
            modifyDTO.setId(studyDTO.getStudy_id());
            return modifyDTO;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return modifyDTO;
    }

    @Override
    public ModifyDTO deleteStudy(Long study_id) throws Exception {
        ModifyDTO modifyDTO = new ModifyDTO();

        try {
            int result = studyRepository.deleteStudyById(study_id);
            modifyDTO.setResult(result);
            modifyDTO.setId(study_id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return modifyDTO;
    }

    @Override
    public StudyDTO findOneById(Long study_id) throws Exception {
        StudyDTO studyDTO = new StudyDTO();
        try {
            Study study = studyRepository.findOneById(study_id);

            if (study == null) return studyDTO;
            else return new StudyDTO(study);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return studyDTO;
    }

    @Override
    public PageResultDTO findAll(Pageable pageable) throws Exception {

        PageResultDTO pageResultDTO = new PageResultDTO();

        try {
            Page<Study> studies = studyRepository.findAll(pageable);
            if (studies.isEmpty()) return pageResultDTO;
            pageResultDTO
                    .setContents(studies.stream()
                    .map(StudyDTO::new)
                    .collect(Collectors.toList())
                    );
            pageResultDTO.setTotalPages(studies.getTotalPages());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pageResultDTO;
    }
}
