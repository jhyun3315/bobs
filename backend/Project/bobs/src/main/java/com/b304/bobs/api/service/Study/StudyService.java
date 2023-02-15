package com.b304.bobs.api.service.Study;

import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.response.Study.StudyMeetRes;
import com.b304.bobs.api.response.Study.StudyModifyRes;
import com.b304.bobs.api.response.Study.StudyRes;
import org.springframework.data.domain.Pageable;
import com.b304.bobs.api.request.Study.StudyReq;
public interface StudyService {

     ModifyRes lockStudy(Long study_id) throws Exception;
     StudyRes createStudy(StudyReq studyReq) throws Exception;
     ModifyRes deleteStudy(Long study_id) throws Exception;
     StudyModifyRes modifyStudy(StudyReq studyReq) throws Exception;
     StudyReq findOneById(Long study_id) throws Exception;
     PageRes findAll(Pageable pageable) throws Exception;
     PageRes findFullAll(Pageable pageable) throws Exception;
     StudyMeetRes studyOnair(Long sutdy_id, boolean study_onair) throws  Exception;

}
