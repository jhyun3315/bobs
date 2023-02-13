package com.b304.bobs.api.service;

import com.b304.bobs.api.request.StudyCommentModifyReq;
import com.b304.bobs.api.request.StudyCommentReq;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.response.StudyCommentRes;
import org.springframework.stereotype.Service;

@Service
public interface StudyCommentService {
    StudyCommentRes createComment(StudyCommentReq studyCommentReq) throws Exception;
    ModifyRes deleteComment(Long study_id) throws Exception;
    ModifyRes modifyComment(StudyCommentModifyReq studyCommentModifyReq) throws Exception;
    PageRes findAll(Long comment_id) throws Exception;
    StudyCommentRes findById(Long study_comment_id) throws Exception;
}
