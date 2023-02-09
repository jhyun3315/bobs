package com.b304.bobs.api.service;

import com.b304.bobs.api.dto.CommunityCommentDTO;
import com.b304.bobs.api.dto.ModifyDTO;
import com.b304.bobs.api.dto.PageResultDTO;

public interface CommunityCommentService {
    public CommunityCommentDTO createComment(CommunityCommentDTO communityCommentDTO) throws  Exception;
    public ModifyDTO deleteComment(Long comment_id) throws Exception;
    public ModifyDTO modifyComment(CommunityCommentDTO communityCommentDTO) throws Exception;
    public PageResultDTO findAll(Long comment_id) throws Exception;
}
