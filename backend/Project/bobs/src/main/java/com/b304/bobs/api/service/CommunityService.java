package com.b304.bobs.api.service;

import com.b304.bobs.api.request.CommunityReq;
import com.b304.bobs.api.response.CommunityRes;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface CommunityService {

    CommunityRes createCommunity(CommunityReq communityDTO) throws Exception;
    ModifyRes deleteCommunity(Long community_id) throws Exception;
    ModifyRes modifyCommunity(CommunityReq communityDTO) throws Exception;
    CommunityRes findOneById(Long community_id) throws Exception;
    PageRes findAll(Pageable pageable) throws Exception;
    PageRes findByUser(Long user_id, Pageable pageable) throws Exception;

}