package com.b304.bobs.api.service;

import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import org.springframework.data.domain.Pageable;
import com.b304.bobs.api.request.StudyReq;
public interface StudyService {

    public StudyReq createStudy(StudyReq studyReq) throws Exception;
    public ModifyRes deleteStudy(Long study_id) throws Exception;
    public ModifyRes modifyStudy(StudyReq studyReq) throws Exception;
    public StudyReq findOneById(Long study_id) throws Exception;
    public PageRes findAll(Pageable pageable) throws Exception;

}
