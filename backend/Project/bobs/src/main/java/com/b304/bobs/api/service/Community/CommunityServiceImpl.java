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
        Long user_id = communityReq.getUser_id();

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
            if(userRepository.findById(communityReq.getUser_id()).isPresent()){
               community.setUser(userRepository.findById(communityReq.getUser_id()).orElse(null));
                result = new CommunityRes(communityRepository.save(community), user_id);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ModifyRes modifyCommunity(CommunityReq communityReq, boolean check_update) throws Exception {
        ModifyRes modifyRes = new ModifyRes();
        int result =0;
        String dirName ="";
        String uploadImageUrl= null;

        try {
            Community community = communityRepository.findOneById(communityReq.getCommunity_id());

            // 기존의 파일 그대로라면,
            if(!check_update){
                uploadImageUrl = community.getCommunity_img();

            //새로운 파일 이라면,
            }else{
                dirName = "/"+ communityReq.getUser_id();
                uploadImageUrl = s3Upload.upload(communityReq.getCommunity_img(), dirName);
            }

            result = communityRepository.modifyCommunity(
                    communityReq.getCommunity_id(),
                    communityReq.getCommunity_title(),
                    communityReq.getCommunity_content(),
                    uploadImageUrl);

            modifyRes.setResult(result);
            if(result == 1) {
                modifyRes.setContent(new CommunityRes(community, uploadImageUrl,communityReq.getUser_id()));
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
    public PageRes findByUser(Long user_id) throws Exception{
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

    @Override
    @Transactional(readOnly = true)
    public CommunityRes findOneById(Long community_id, Long user_id) throws Exception {
        CommunityRes communityRes = new CommunityRes();

        try {
            Community community = communityRepository.findOneById(community_id);

            if(community == null) return communityRes;
            else return new CommunityRes(community, user_id);

        }catch (Exception e){
            e.printStackTrace();
        }
        return communityRes;
    }

}
