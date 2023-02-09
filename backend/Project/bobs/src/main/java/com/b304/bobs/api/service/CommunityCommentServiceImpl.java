package com.b304.bobs.api.service;

import com.b304.bobs.api.response.CommunityCommentRes;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.db.entity.CommunityComment;
import com.b304.bobs.db.repository.CommunityCommentRepository;
import com.b304.bobs.db.repository.CommunityRepository;
import com.b304.bobs.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityCommentServiceImpl implements CommunityCommentService{

    final private CommunityCommentRepository communityCommentRepository;
    final private UserRepository userRepository;
    final private CommunityRepository communityRepository;

    @Override
    public CommunityCommentRes createComment(CommunityCommentRes communityCommentRes) throws Exception {
        CommunityComment communityComment = new CommunityComment();
        CommunityCommentRes result = new CommunityCommentRes();

        try  {
            communityComment.setCommunity_comment_content(communityCommentRes.getCommunity_comment_content());
            communityComment.setCommunity_comment_deleted(false);
            communityComment.setCommunity_comment_created(LocalDateTime.now());

            if(userRepository.findById(communityCommentRes.getUser_id()).isPresent()){
                communityComment.setUser(userRepository.findById(communityCommentRes.getUser_id()).orElse(null));

                if(communityRepository.findById(communityCommentRes.getCommunity_id()).isPresent()){
                    communityComment.setCommunity(communityRepository.findById(communityCommentRes.getCommunity_id()).orElse(null));
                    result = new CommunityCommentRes(communityCommentRepository.save(communityComment));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ModifyRes deleteComment(Long community_comment_id) throws Exception {
        ModifyRes modifyRes = new ModifyRes();

        try {
            System.out.println(community_comment_id);
            int result = communityCommentRepository.deleteComment(community_comment_id);
            modifyRes.setResult(result);
            modifyRes.setId(community_comment_id);
            return modifyRes;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return modifyRes;
    }

    @Override
    public ModifyRes modifyComment(CommunityCommentRes communityCommentRes) throws Exception {
        ModifyRes modifyRes = new ModifyRes();

        try {
            int result = communityCommentRepository.modifyComment(
                    communityCommentRes.getCommunity_comment_content(),
                    communityCommentRes.getCommunity_comment_id()
            );

            modifyRes.setResult(result);
            modifyRes.setId(communityCommentRes.getCommunity_id());
            return modifyRes;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return modifyRes;
    }


    @Override
    public PageRes findAll(Long comment_id) throws Exception {
        PageRes pageRes = new PageRes();

        try {
            List<CommunityComment> comments = communityCommentRepository.findAll(comment_id);
            if(comments.isEmpty()) return pageRes;

            pageRes
                    .setContents(comments.stream()
                            .map(CommunityCommentRes::new)
                            .collect(Collectors.toList())
                    );
        }catch (Exception e){
            e.printStackTrace();
        }
        return pageRes;
    }

}
