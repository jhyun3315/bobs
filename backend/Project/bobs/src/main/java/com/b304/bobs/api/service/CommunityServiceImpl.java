package com.b304.bobs.api.service;

import com.b304.bobs.api.dto.ModifyDTO;
import com.b304.bobs.api.dto.PageResultDTO;
import com.b304.bobs.db.entity.Community;
import com.b304.bobs.api.dto.CommunityDTO;
import com.b304.bobs.db.repository.CommunityRepository;
import com.b304.bobs.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;

    @Override
    public CommunityDTO createCommunity(CommunityDTO communityDTO) throws Exception {
        Community community = new Community();
        CommunityDTO result = new CommunityDTO();

        try {
            community.setCommunity_content(communityDTO.getCommunity_content());
            community.setCommunity_title(communityDTO.getCommunity_title());
            community.setCommunity_img(communityDTO.getCommunity_img());
            community.setCommunity_createdTime(LocalDateTime.now());
            community.setCommunity_deleted(false);
            community.setCommunity_hit(0);

            if(userRepository.findById(communityDTO.getUser_id()).isPresent()){
                community.setUser(userRepository.findById(communityDTO.getUser_id()).orElse(null));
                result = new CommunityDTO(communityRepository.save(community));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ModifyDTO modifyCommunity(CommunityDTO communityDTO) throws Exception {
        ModifyDTO modifyDTO = new ModifyDTO();

        try {
            int result = communityRepository.modifyCommunity(
                    communityDTO.getCommunity_id(),
                    communityDTO.getCommunity_title(),
                    communityDTO.getCommunity_content(),
                    communityDTO.getCommunity_img());

            modifyDTO.setResult(result);
            modifyDTO.setId(communityDTO.getCommunity_id());
            return modifyDTO;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return modifyDTO;
    }

    @Override
    public ModifyDTO deleteCommunity(Long community_id) throws Exception {
        ModifyDTO modifyDTO = new ModifyDTO();

        try {
           int result = communityRepository.deleteCommunityById(community_id);
            modifyDTO.setResult(result);
            modifyDTO.setId(community_id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return modifyDTO;
    }

    @Override
    public CommunityDTO findOneById(Long community_id) throws Exception {
        CommunityDTO communityDTO = new CommunityDTO();
        try {
            Community community = communityRepository.findOneById(community_id);

            if(community == null) return communityDTO;
            else return new CommunityDTO(community);

        }catch (Exception e){
            e.printStackTrace();
        }
        return communityDTO;
    }

    @Override
    public PageResultDTO findAll(Pageable pageable) throws Exception{
        PageResultDTO pageResultDTO = new PageResultDTO();

        try {
            Page<Community> communities = communityRepository.findAll(pageable);
            if(communities.isEmpty()) return pageResultDTO;
            pageResultDTO
                    .setContents(communities.stream()
                    .map(CommunityDTO::new)
                    .collect(Collectors.toList())
            );
            pageResultDTO.setTotalPages(communities.getTotalPages());
        }catch (Exception e){
            e.printStackTrace();
        }
        return pageResultDTO;
    }

    @Override
    public PageResultDTO findByUser(Long user_id, Pageable pageable) throws Exception{
        PageResultDTO pageResultDTO = new PageResultDTO();

        try {
            Page<Community> communities = communityRepository.findByUser(user_id, pageable);
            if(communities.isEmpty()) return pageResultDTO;
            pageResultDTO
                    .setContents(communities.stream()
                            .map(CommunityDTO::new)
                            .collect(Collectors.toList())
                    );
            pageResultDTO.setTotalPages(communities.getTotalPages());
        }catch (Exception e){
            e.printStackTrace();
        }
        return pageResultDTO;
    }
}
