package com.b304.bobs.api.service.Community;

import com.b304.bobs.api.request.Community.CommunityReq;
import com.b304.bobs.api.response.Community.CommunityOneRes;
import com.b304.bobs.api.response.Community.CommunityRes;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import org.springframework.stereotype.Service;

@Service
public interface CommunityService {

    CommunityRes createCommunity(CommunityReq communityDTO) throws Exception;
    ModifyRes deleteCommunity(Long community_id) throws Exception;
    ModifyRes modifyCommunity(CommunityReq communityReq, boolean check_update) throws Exception;
    CommunityRes findOneById(Long community_id, Long user_id) throws Exception;
    PageRes findAll() throws Exception;
    PageRes findByUser(Long user_id) throws Exception;

}