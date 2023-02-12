package com.b304.bobs.api.service;

import com.b304.bobs.api.request.CommunityCommentReq;
import com.b304.bobs.api.response.CommunityCommentRes;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;

public interface CommunityCommentService {
    public CommunityCommentRes createComment(CommunityCommentReq communityCommentReq) throws  Exception;
    public ModifyRes deleteComment(Long comment_id) throws Exception;
    public ModifyRes modifyComment(CommunityCommentRes communityCommentRes) throws Exception;
    public PageRes findAll(Long comment_id) throws Exception;
}
