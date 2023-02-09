package com.b304.bobs.api.service;

import com.b304.bobs.api.dto.CommunityDTO;
import com.b304.bobs.api.dto.ModifyDTO;
import com.b304.bobs.api.dto.PageResultDTO;
import org.springframework.data.domain.Pageable;

public interface CommunityService {

    public CommunityDTO createCommunity(CommunityDTO communityDTO) throws Exception;
    public ModifyDTO deleteCommunity(Long community_id) throws Exception;
    public ModifyDTO modifyCommunity(CommunityDTO communityDTO) throws Exception;
    public CommunityDTO findOneById(Long community_id) throws Exception;
    public PageResultDTO findAll(Pageable pageable) throws Exception;
    public PageResultDTO findByUser(Long user_id, Pageable pageable) throws Exception;

}