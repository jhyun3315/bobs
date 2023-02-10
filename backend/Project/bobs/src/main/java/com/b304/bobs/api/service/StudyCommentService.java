package com.b304.bobs.api.service;

import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.response.StudyCommentRes;

public interface StudyCommentService {
    public StudyCommentRes createComment(StudyCommentRes studyCommentRes) throws Exception;
    public ModifyRes deleteComment(Long study_id) throws Exception;
    public ModifyRes modifyComment(StudyCommentRes studyCommentRes) throws Exception;
    public PageRes findAll(Long comment_id) throws Exception;
}
