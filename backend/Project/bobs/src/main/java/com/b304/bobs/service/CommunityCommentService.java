package com.b304.bobs.service;

import com.b304.bobs.dto.CommunityCommentDTO;
import com.b304.bobs.dto.ModifyDTO;
import com.b304.bobs.dto.PageResultDTO;
import org.springframework.data.domain.Pageable;

public interface CommunityCommentService {
    public CommunityCommentDTO createComment(CommunityCommentDTO communityCommentDTO) throws  Exception;
    public CommunityCommentDTO deleteComment(CommunityCommentDTO communityCommentDTO) throws Exception;
    public ModifyDTO modifyComment(CommunityCommentDTO communityCommentDTO) throws Exception;
    public PageResultDTO findAll(Long comment_id, Pageable pageable) throws Exception;
}
