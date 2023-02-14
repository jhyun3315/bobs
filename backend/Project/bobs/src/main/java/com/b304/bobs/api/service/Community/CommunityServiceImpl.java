package com.b304.bobs.api.service.Community;

import com.b304.bobs.api.response.Community.CommunityOneRes;
import com.b304.bobs.api.response.Community.CommunityRes;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.db.entity.Community;
import com.b304.bobs.api.request.Community.CommunityReq;
import com.b304.bobs.db.repository.CommunityCommentRepository;
import com.b304.bobs.db.repository.CommunityRepository;
import com.b304.bobs.db.repository.UserRepository;
import com.b304.bobs.s3.S3Upload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;
    private final CommunityCommentRepository communityCommentRepository;
    private final UserRepository userRepository;
    private final S3Upload s3Upload;

    @Override
    public CommunityRes createCommunity(CommunityReq communityReq) throws Exception {
        Community community = new Community();
        CommunityRes result = new CommunityRes();
        String uploadImageUrl = "";

        try {
            if(!communityReq.getCommunity_img().isEmpty()){
                String dirName = communityReq.getUser_id() +"/";
                uploadImageUrl = s3Upload.upload(communityReq.getCommunity_img(), dirName);

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
        String dirName ="";
        String uploadImageUrl= null;

        try {
            Community community = communityRepository.findOneById(communityReq.getCommunity_id());
            String originalFilename = communityReq.getCommunity_img().getOriginalFilename();

            // 새로운 파일이 등록 되었다면,
            if(originalFilename!=null && !originalFilename.equals("")){
                dirName = "/"+ communityReq.getCommunity_id();
                uploadImageUrl = s3Upload.upload(communityReq.getCommunity_img(), dirName);
            }else{
                // 이전과 같은 파일이라면 기존의 파일명 입력
                uploadImageUrl = community.getCommunity_img();
            }

            result = communityRepository.modifyCommunity(
                    communityReq.getCommunity_id(),
                    communityReq.getCommunity_title(),
                    communityReq.getCommunity_content(),
                    uploadImageUrl);

            modifyRes.setResult(result);
            if(result == 1) {
                modifyRes.setContent(new CommunityRes(community, uploadImageUrl));
            }
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
           if(result==1) communityCommentRepository.deleteCommunityCommentsById(community_id);
            modifyRes.setResult(result);
            modifyRes.setId(community_id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return modifyRes;
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public CommunityOneRes findOne(Long community_id) throws Exception {
        CommunityOneRes communityOneRes = new CommunityOneRes();

        try {
            Community community = communityRepository.findOneById(community_id);

            if(community == null) return communityOneRes;
            else return new CommunityOneRes(community);

        }catch (Exception e){
            e.printStackTrace();
        }
        return communityOneRes;
    }

    @Override
    @Transactional(readOnly = true)
    public PageRes findAll( ) throws Exception{
        PageRes pageRes = new PageRes();

        try {
            List<Community> communities = communityRepository.findAll();
            if(communities.isEmpty()) return pageRes;
            pageRes.setContents(communities.stream()
                    .map(CommunityRes::new)
                    .collect(Collectors.toList())
            );
        }catch (Exception e){
            e.printStackTrace();
        }
        return pageRes;
    }

    @Override
    @Transactional(readOnly = true)
    public PageRes findByUser(Long user_id ) throws Exception{
        PageRes pageRes = new PageRes();

        try {
            List<Community> communities = communityRepository.findByUser(user_id);
            if(communities.isEmpty()) return pageRes;
            pageRes
                    .setContents(communities.stream()
                            .map(CommunityRes::new)
                            .collect(Collectors.toList())
                    );
        }catch (Exception e){
            e.printStackTrace();
        }
        return pageRes;
    }
}
