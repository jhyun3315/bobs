package com.b304.bobs.service;

import com.b304.bobs.entity.Community;
import com.b304.bobs.repository.CommunityDTO;
import com.b304.bobs.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;
    private final EntityManager em;

    @Override
    public ResponseEntity<Map<String, Object>> createCommunity(CommunityDTO communityDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String,Object>> findOneById(Long community_id) {
        HttpStatus status = null;
        Map<String, Object> resultMap = new HashMap<>();
        Community community = communityRepository.findOneBiyId(community_id);

        try{
            if(community !=null){
                resultMap.put("community", community);

                resultMap.put("user_id",community.getUser().getUser_id());
                resultMap.put("community_title",  community.getCommunity_title());
                resultMap.put("community_content", community.getCommunity_content());
                resultMap.put("community_img",community.getCommunity_img());
                resultMap.put("community_id",community_id);

                status = HttpStatus.OK;
            }else{
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }catch (Exception e){
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap,status);
    }

    @Override
    public ResponseEntity<List<Map<String, Object>>> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<List<Map<String, Object>>> findAllById(Long id) {
        return null;
    }
}
