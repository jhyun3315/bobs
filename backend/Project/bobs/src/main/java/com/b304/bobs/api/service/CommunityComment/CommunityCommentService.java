package com.b304.bobs.api.service.CommunityComment;

import com.b304.bobs.api.request.CommunityComment.CommentCheckReq;
import com.b304.bobs.api.request.CommunityComment.CommunityCommentCreatReq;
import com.b304.bobs.api.request.CommunityComment.CommunityCommentModifyReq;
import com.b304.bobs.api.request.CommunityComment.CommunityCommentGetReq;
import com.b304.bobs.api.response.CommunityComment.CommunityCommentRes;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;

public interface CommunityCommentService {
    CommunityCommentRes createComment(CommunityCommentCreatReq communityCommentCreatReq) throws  Exception;
    ModifyRes deleteComment(Long comment_id) throws Exception;
    CommunityCommentRes modifyComment(CommunityCommentModifyReq communityCommentModifyReq) throws Exception;
    PageRes findAll(CommunityCommentGetReq communityCommentGetReq) throws Exception;
    CommunityCommentRes findById(CommentCheckReq commentCheckReq) throws Exception;
}
