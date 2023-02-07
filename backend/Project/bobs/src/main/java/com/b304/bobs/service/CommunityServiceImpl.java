package com.b304.bobs.service;

import com.b304.bobs.dto.CommunityUpload;
import com.b304.bobs.entity.Community;
import com.b304.bobs.dto.CommunityDTO;
import com.b304.bobs.entity.CommunityComment;
import com.b304.bobs.repository.CommunityRepository;
import com.b304.bobs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;

    @Override
    public CommunityDTO deleteCommunity(Long community_id) throws Exception {
        CommunityDTO result = new CommunityDTO();

        try {
            result = new CommunityDTO(communityRepository.deleteCommunityById(community_id));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public CommunityDTO createCommunity(CommunityDTO communityDTO) throws Exception {
        Community community = new Community();

        try {
            community.setCommunity_content(communityDTO.getCommunity_content());
            community.setCommunity_title(communityDTO.getCommunity_title());
            community.setCommunity_img(communityDTO.getCommunity_img());
            community.setCommunity_createdTime(LocalDateTime.now());
            community.setCommunity_deleted(false);
            community.setCommunity_hit(0);
            community.setUser(userRepository.findById(communityDTO.getUser_id()).orElse(null));

            return new CommunityDTO(communityRepository.save(community));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return communityDTO;
    }

    @Override
    public CommunityDTO modifyCommunity(CommunityDTO communityDTO) throws Exception {
        Community community = new Community();

        try {
            community.setCommunity_id(communityDTO.getCommunity_id());
            community.setCommunity_content(communityDTO.getCommunity_content());
            community.setCommunity_title(communityDTO.getCommunity_title());
            community.setCommunity_img(communityDTO.getCommunity_img());
            community.setCommunity_createdTime(communityDTO.getCommunity_created());
            community.setCommunity_deleted(false);
            community.setCommunity_hit(communityDTO.getCommunity_hit());
            community.setUser(userRepository.findById(communityDTO.getUser_id()).orElse(null));

            return new CommunityDTO(communityRepository.save(community));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return communityDTO;
    }


    @Override
    public Map<String, Object> findOneById(Long community_id) throws Exception {
        Map<String, Object> result = new HashMap<>();

        try {
            Community community = communityRepository.findOneById(community_id);

            if(community.getCommunity_id()==null) return result;
            else{
                result.put("community",new CommunityDTO(community));
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Page<Community> findAll(Pageable pageable) throws Exception{
//        List<Community> contents = new ArrayList<>();
//
//        try {
//            Page<Community> communities = communityRepository.findAll(pageable);
//            contents = communities.getContent();
//            communities.getTotalPages()
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        return communityRepository.findAll(pageable);
    }

    @Override
    public Page<Community> findByUser(Long user_id, Pageable pageable) throws Exception{
//        List<CommunityDTO> result = new ArrayList<>();
//
//        try {
//            Page<Community> communities = communityRepository.findByUser(user_id, pageable);
//            result = communities.stream()
//                    .map(CommunityDTO::new)
//                    .collect(Collectors.toList());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        return communityRepository.findByUser(user_id, pageable);
    }
}
