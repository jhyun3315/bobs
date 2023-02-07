package com.b304.bobs.service;

import com.b304.bobs.dto.ModifyDTO;
import com.b304.bobs.dto.StudyDTO;

import java.awt.print.Pageable;

public interface StudyService {

    public StudyDTO createStudy(StudyDTO studyDTO) throws Exception;
    public ModifyDTO deleteStudy(Long study_id) throws Exception;
    public ModifyDTO modifyStudy(StudyDTO studyDTO) throws Exception;
    public StudyDTO findOneById(Long study_id) throws Exception;
    public StudyDTO findAll(Pageable pageable) throws Exception;

}
