package com.b304.bobs.service;

import com.b304.bobs.dto.CommunityCommentDTO;
import com.b304.bobs.dto.ModifyDTO;
import com.b304.bobs.dto.PageResultDTO;

import java.util.List;

public interface CommunityCommentService {
    public CommunityCommentDTO createComment(CommunityCommentDTO communityCommentDTO) throws  Exception;
    public ModifyDTO deleteComment(Long comment_id) throws Exception;
    public ModifyDTO modifyComment(CommunityCommentDTO communityCommentDTO) throws Exception;
    public PageResultDTO findAll(Long comment_id) throws Exception;
}
