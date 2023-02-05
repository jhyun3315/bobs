package com.b304.bobs.service;

import com.b304.bobs.entity.Community;
import com.b304.bobs.repository.CommunityDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CommunityService {

    public ResponseEntity<Map<String, Object>> createCommunity(CommunityDTO communityDTO);
    public ResponseEntity<Map<String, Object>> findOneById(Long community_id);
    public ResponseEntity<List<Map<String, Object>>> findAll(Pageable pageable);
    public ResponseEntity<List<Map<String,Object>>> findAllById(Long id);

}
