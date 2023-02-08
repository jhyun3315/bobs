package com.b304.bobs.service;

import com.b304.bobs.dto.ModifyDTO;
import com.b304.bobs.dto.PageResultDTO;
import org.springframework.data.domain.Pageable;
import com.b304.bobs.dto.StudyDTO;
public interface StudyService {

    public StudyDTO createStudy(StudyDTO studyDTO) throws Exception;
    public ModifyDTO deleteStudy(Long study_id) throws Exception;
    public ModifyDTO modifyStudy(StudyDTO studyDTO) throws Exception;
    public StudyDTO findOneById(Long study_id) throws Exception;
    public PageResultDTO findAll(Pageable pageable) throws Exception;

}
