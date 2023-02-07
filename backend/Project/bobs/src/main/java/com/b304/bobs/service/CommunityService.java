package com.b304.bobs.service;

import com.b304.bobs.dto.CommunityDTO;
import com.b304.bobs.dto.ModifyDTO;
import com.b304.bobs.dto.PageResultDTO;
import org.springframework.data.domain.Pageable;

public interface CommunityService {

    public CommunityDTO createCommunity(CommunityDTO communityDTO) throws Exception;
    public ModifyDTO deleteCommunity(Long community_id) throws Exception;
    public ModifyDTO modifyCommunity(CommunityDTO communityDTO) throws Exception;
    public CommunityDTO findOneById(Long community_id) throws Exception;
    public PageResultDTO findAll(Pageable pageable) throws Exception;
    public PageResultDTO findByUser(Long user_id, Pageable pageable) throws Exception;

}