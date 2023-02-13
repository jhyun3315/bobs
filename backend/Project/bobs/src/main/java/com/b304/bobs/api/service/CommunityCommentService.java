package com.b304.bobs.api.service;

import com.b304.bobs.api.request.CommunityCommentReq;
import com.b304.bobs.api.response.CommunityCommentRes;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;

public interface CommunityCommentService {
    CommunityCommentRes createComment(CommunityCommentReq communityCommentReq) throws  Exception;
    ModifyRes deleteComment(Long comment_id) throws Exception;
    ModifyRes modifyComment(CommunityCommentRes communityCommentRes) throws Exception;
    PageRes findAll(Long comment_id) throws Exception;
    CommunityCommentRes findById(Long user_is) throws Exception;
}
