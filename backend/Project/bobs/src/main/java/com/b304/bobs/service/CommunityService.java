package com.b304.bobs.service;

import com.b304.bobs.entity.Community;
import com.b304.bobs.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;

    public void write(Community community){
        communityRepository.write(community);
    }

    @Transactional(readOnly = true)
    public List<Community> findAllById(Long user_id){
        return communityRepository.findAllById(user_id);
    }

    @Transactional(readOnly = true)
    public List<Community> findAll(){
        return communityRepository.findAll();
    }


}
