package com.b304.bobs.api.service;

import com.b304.bobs.api.request.CommunityReq;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import org.springframework.data.domain.Pageable;

public interface CommunityService {

    public CommunityReq createCommunity(CommunityReq communityDTO) throws Exception;
    public ModifyRes deleteCommunity(Long community_id) throws Exception;
    public ModifyRes modifyCommunity(CommunityReq communityDTO) throws Exception;
    public CommunityReq findOneById(Long community_id) throws Exception;
    public PageRes findAll(Pageable pageable) throws Exception;
    public PageRes findByUser(Long user_id, Pageable pageable) throws Exception;

}