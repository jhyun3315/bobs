package com.b304.bobs.api.service;

import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.db.entity.Community;
import com.b304.bobs.api.request.CommunityReq;
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
    public CommunityReq createCommunity(CommunityReq communityDTO) throws Exception {
        Community community = new Community();
        CommunityReq result = new CommunityReq();

        try {
            community.setCommunity_content(communityDTO.getCommunity_content());
            community.setCommunity_title(communityDTO.getCommunity_title());
            community.setCommunity_img(communityDTO.getCommunity_img());
            community.setCommunity_createdTime(LocalDateTime.now());
            community.setCommunity_deleted(false);
            community.setCommunity_hit(0);

            if(userRepository.findById(communityDTO.getUser_id()).isPresent()){
                community.setUser(userRepository.findById(communityDTO.getUser_id()).orElse(null));
                result = new CommunityReq(communityRepository.save(community));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ModifyRes modifyCommunity(CommunityReq communityDTO) throws Exception {
        ModifyRes modifyRes = new ModifyRes();

        try {
            int result = communityRepository.modifyCommunity(
                    communityDTO.getCommunity_id(),
                    communityDTO.getCommunity_title(),
                    communityDTO.getCommunity_content(),
                    communityDTO.getCommunity_img());

            modifyRes.setResult(result);
            modifyRes.setId(communityDTO.getCommunity_id());
            return modifyRes;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return modifyRes;
    }

    @Override
    public ModifyRes deleteCommunity(Long community_id) throws Exception {
        ModifyRes modifyRes = new ModifyRes();

        try {
           int result = communityRepository.deleteCommunityById(community_id);
            modifyRes.setResult(result);
            modifyRes.setId(community_id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return modifyRes;
    }

    @Override
    public CommunityReq findOneById(Long community_id) throws Exception {
        CommunityReq communityDTO = new CommunityReq();
        try {
            Community community = communityRepository.findOneById(community_id);

            if(community == null) return communityDTO;
            else return new CommunityReq(community);

        }catch (Exception e){
            e.printStackTrace();
        }
        return communityDTO;
    }

    @Override
    public PageRes findAll(Pageable pageable) throws Exception{
        PageRes pageRes = new PageRes();

        try {
            Page<Community> communities = communityRepository.findAll(pageable);
            if(communities.isEmpty()) return pageRes;
            pageRes
                    .setContents(communities.stream()
                    .map(CommunityReq::new)
                    .collect(Collectors.toList())
            );
            pageRes.setTotalPages(communities.getTotalPages());
        }catch (Exception e){
            e.printStackTrace();
        }
        return pageRes;
    }

    @Override
    public PageRes findByUser(Long user_id, Pageable pageable) throws Exception{
        PageRes pageRes = new PageRes();

        try {
            Page<Community> communities = communityRepository.findByUser(user_id, pageable);
            if(communities.isEmpty()) return pageRes;
            pageRes
                    .setContents(communities.stream()
                            .map(CommunityReq::new)
                            .collect(Collectors.toList())
                    );
            pageRes.setTotalPages(communities.getTotalPages());
        }catch (Exception e){
            e.printStackTrace();
        }
        return pageRes;
    }
}
