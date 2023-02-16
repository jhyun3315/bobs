package com.b304.bobs.api.service.StudyComment;

import com.b304.bobs.api.request.StudyComment.StudyCommentListReq;
import com.b304.bobs.api.request.StudyComment.StudyCommentModifyReq;
import com.b304.bobs.api.request.StudyComment.StudyCommentReq;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.response.StudyComment.StudyCommentRes;
import org.springframework.stereotype.Service;

@Service
public interface StudyCommentService {
    StudyCommentRes createComment(StudyCommentReq studyCommentReq) throws Exception;
    ModifyRes deleteComment(Long study_id) throws Exception;
    ModifyRes modifyComment(StudyCommentModifyReq studyCommentModifyReq) throws Exception;
    PageRes findAll(StudyCommentListReq studyCommentListReq) throws Exception;
    StudyCommentRes findById(Long study_comment_id) throws Exception;
}
