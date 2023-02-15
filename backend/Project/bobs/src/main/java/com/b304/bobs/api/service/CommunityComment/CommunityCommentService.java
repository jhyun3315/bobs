package com.b304.bobs.api.service.CommunityComment;

import com.b304.bobs.api.request.CommunityComment.CommentCheckReq;
import com.b304.bobs.api.request.CommunityComment.CommunityCommentModiReq;
import com.b304.bobs.api.request.CommunityComment.CommunityCommentReq;
import com.b304.bobs.api.response.CommunityComment.CommunityCommentRes;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;

public interface CommunityCommentService {
    CommunityCommentRes createComment(CommunityCommentReq communityCommentReq) throws  Exception;
    ModifyRes deleteComment(Long comment_id) throws Exception;
    CommunityCommentRes modifyComment(CommunityCommentModiReq communityCommentModiReq) throws Exception;
    PageRes findAll(CommentCheckReq commentCheckReq) throws Exception;
    CommunityCommentRes findById(CommentCheckReq commentCheckReq) throws Exception;
}
