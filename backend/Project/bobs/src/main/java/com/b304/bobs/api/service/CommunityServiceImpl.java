package com.b304.bobs.api.service;

import com.b304.bobs.api.response.CommunityRes;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.db.entity.Community;
import com.b304.bobs.api.request.CommunityReq;
import com.b304.bobs.db.entity.User;
import com.b304.bobs.db.repository.CommunityRepository;
import com.b304.bobs.db.repository.UserRepository;
import com.b304.bobs.s3.S3Service;
import com.b304.bobs.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;
    private final S3Uploader s3Uploader;

    @Override
    public CommunityRes createCommunity(CommunityReq communityReq) throws Exception {
        Community community = new Community();
        CommunityRes result = new CommunityRes();
        String uploadImageUrl = "";

        try {
            if(!communityReq.getCommunity_img().isEmpty()){
                String dirName = Long.toString(communityReq.getUser_id());
                uploadImageUrl = s3Uploader.upload(communityReq.getCommunity_img(), dirName);

                community.setCommunity_img(uploadImageUrl);
            }

            community.setCommunity_content(communityReq.getCommunity_content());
            community.setCommunity_title(communityReq.getCommunity_title());
            community.setCommunity_createdTime(LocalDateTime.now());
            community.setCommunity_deleted(false);
            community.setCommunity_hit(0);

            if(userRepository.findById(communityReq.getUser_id()).isPresent()){
               community.setUser(userRepository.findById(communityReq.getUser_id()).orElse(null));
                result = new CommunityRes(communityRepository.save(community));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ModifyRes modifyCommunity(CommunityReq communityReq) throws Exception {
        ModifyRes modifyRes = new ModifyRes();
        int result =0;

        try {
            String userName = communityReq.getUser_name();
            String dirName = "/"+userName;
            String uploadImageUrl = s3Uploader.upload(communityReq.getCommunity_img(), dirName);

            result = communityRepository.modifyCommunity(
                    communityReq.getCommunity_id(),
                    communityReq.getCommunity_title(),
                    communityReq.getCommunity_content(),
                    uploadImageUrl);

            modifyRes.setResult(result);
            if(result == 1) modifyRes.setContent(new CommunityRes(communityReq, uploadImageUrl));
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
    public CommunityRes findOneById(Long community_id) throws Exception {
        CommunityRes communityRes = new CommunityRes();

        try {
            Community community = communityRepository.findOneById(community_id);

            if(community == null) return communityRes;
            else return new CommunityRes(community);

        }catch (Exception e){
            e.printStackTrace();
        }
        return communityRes;
    }

    @Override
    public PageRes findAll(Pageable pageable) throws Exception{
        PageRes pageRes = new PageRes();

        try {
            Page<Community> communities = communityRepository.findAll(pageable);
            if(communities.isEmpty()) return pageRes;
            pageRes.setContents(communities.stream()
                    .map(CommunityRes::new)
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
                            .map(CommunityRes::new)
                            .collect(Collectors.toList())
                    );
            pageRes.setTotalPages(communities.getTotalPages());
        }catch (Exception e){
            e.printStackTrace();
        }
        return pageRes;
    }
}
