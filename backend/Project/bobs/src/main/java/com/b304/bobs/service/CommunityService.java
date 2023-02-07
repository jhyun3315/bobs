package com.b304.bobs.service;

import com.b304.bobs.dto.CommunityDTO;
import com.b304.bobs.dto.CommunityUpload;
import com.b304.bobs.entity.Community;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface CommunityService {

    public CommunityDTO deleteCommunity(Long community_id) throws Exception;
    public CommunityDTO createCommunity(CommunityDTO communityDTO) throws Exception;
    public CommunityDTO modifyCommunity(CommunityDTO communityDTO) throws Exception;
    public Map<String, Object> findOneById(Long community_id) throws Exception;
    public Page<Community> findAll(Pageable pageable) throws Exception;
    public Page<Community> findByUser(Long user_id, Pageable pageable) throws Exception;

}