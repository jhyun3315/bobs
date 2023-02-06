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
    public CommunityDTO deleteCommunity(CommunityUpload communityUpload) throws Exception {
        Community community = communityRepository.findOneById(communityUpload.getCommunity_id());
        CommunityDTO communityDTO = new CommunityDTO();

        try {
            community.setCommunity_deleted(true);
            community.setUser(userRepository.findById(communityUpload.getUser_id()).orElse(null));
            communityDTO = new CommunityDTO(communityRepository.save(community));
            return communityDTO;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return communityDTO;
    }

    @Override
    public CommunityDTO createCommunity(CommunityUpload communityUpload) {
        String filePath = "C:/bobs/";
        LocalDateTime created = LocalDateTime.now();
        String fileName = null;

        Community community = new Community();
        CommunityDTO communityDTO = new CommunityDTO();

        try {
            if(!communityUpload.getCommunity_img().isEmpty()){
                fileName = (communityUpload.getUser_name())+ created.toString().replace(":","").replace(".","") +"." + communityUpload.getCommunity_img().getContentType().split("/")[1];
                File saveFile= new File(filePath, fileName);
                communityUpload.getCommunity_img().transferTo(saveFile);
            }

            community.setCommunity_content(communityUpload.getCommunity_content());
            community.setCommunity_title(communityUpload.getCommunity_title());
            community.setCommunity_img(fileName);
            community.setCommunity_createdTime(created);
            community.setCommunity_deleted(false);
            community.setCommunity_hit(0);
            community.setUser(userRepository.findById(communityUpload.getUser_id()).orElse(null));

            return new CommunityDTO(communityRepository.save(community));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return communityDTO;
    }

    @Override
    public CommunityDTO modifyCommunity(CommunityUpload communityUpload) throws Exception {
        String priorFile = communityRepository.findOneById(communityUpload.getCommunity_id()).getCommunity_img();
        String fileName = null;
        LocalDateTime created = LocalDateTime.now();
        CommunityDTO communityDTO = new CommunityDTO();

        fileName = communityUpload.getCommunity_img().getOriginalFilename();
        if(!priorFile.equals(fileName)){
            File forDel = new File(priorFile);
            forDel.delete();
        }

        String filePath = "C:/bobs/";
        Community community = new Community();

        try {

            if(!communityUpload.getCommunity_img().isEmpty()) {
                fileName= (communityUpload.getUser_name())+ (created.toString().replace(":","").replace(".","")) +"." + communityUpload.getCommunity_img().getContentType().split("/")[1];
                File saveFile = new File(filePath, fileName);
                communityUpload.getCommunity_img().transferTo(saveFile);
            }

            community.setCommunity_id(communityUpload.getCommunity_id());
            community.setCommunity_content(communityUpload.getCommunity_content());
            community.setCommunity_title(communityUpload.getCommunity_title());
            community.setCommunity_img(fileName);
            community.setCommunity_createdTime(created);
            community.setCommunity_deleted(false);
            community.setCommunity_hit(0);
            community.setUser(userRepository.findById(communityUpload.getUser_id()).orElse(null));

            return new CommunityDTO(communityRepository.save(community));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return communityDTO;
    }



    @Override
    public Map<String, Object> findOneById(Long community_id) {
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
    public List<CommunityDTO> findAll(Pageable pageable) {
        List<CommunityDTO> result = new ArrayList<>();

        try {
            Page<Community> communities = communityRepository.findAll(pageable);
            result = communities.stream()
                    .map(CommunityDTO::new)
                    .collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<CommunityDTO> findByUser(Long user_id, Pageable pageable) {
        List<CommunityDTO> result = new ArrayList<>();

        try {
            Page<Community> communities = communityRepository.findByUser(user_id, pageable);
            result = communities.stream()
                    .map(CommunityDTO::new)
                    .collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
