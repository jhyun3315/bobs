package com.b304.bobs.api.service;

import com.b304.bobs.api.request.CommunityReq;
import com.b304.bobs.api.response.CommunityRes;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface CommunityService {

    public CommunityRes createCommunity(CommunityReq communityDTO) throws Exception;
    public ModifyRes deleteCommunity(Long community_id) throws Exception;
    public ModifyRes modifyCommunity(CommunityReq communityDTO) throws Exception;
    public CommunityRes findOneById(Long community_id) throws Exception;
    public PageRes findAll(Pageable pageable) throws Exception;
    public PageRes findByUser(Long user_id, Pageable pageable) throws Exception;

}