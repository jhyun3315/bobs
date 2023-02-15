package com.b304.bobs.api.service.CommunityComment;

import com.b304.bobs.api.request.CommunityComment.CommentCheckReq;
import com.b304.bobs.api.request.CommunityComment.CommunityCommentModiReq;
import com.b304.bobs.api.request.CommunityComment.CommunityCommentReq;
import com.b304.bobs.api.response.CommunityComment.CommunityCommentRes;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.db.entity.CommunityComment;
import com.b304.bobs.db.repository.CommunityCommentRepository;
import com.b304.bobs.db.repository.CommunityRepository;
import com.b304.bobs.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommunityCommentServiceImpl implements CommunityCommentService{

    final private CommunityCommentRepository communityCommentRepository;
    final private UserRepository userRepository;
    final private CommunityRepository communityRepository;

    @Override
    public CommunityCommentRes createComment(CommunityCommentReq communityCommentReq) throws Exception {
        CommunityComment communityComment = new CommunityComment();
        CommunityCommentRes result = new CommunityCommentRes();
        Long user_id = communityCommentReq.getUser_id();

        System.out.println("변경 내용:" + communityCommentReq.getCommunity_comment_content());

        try  {
            communityComment.setCommunity_comment_content(communityCommentReq.getCommunity_comment_content());
            communityComment.setCommunity_comment_created(LocalDateTime.now());
            communityComment.setUser(userRepository.findById(communityCommentReq.getUser_id()).orElse(null));
            communityComment.setCommunity(communityRepository.findById(user_id).orElse(null));
            result = new CommunityCommentRes(communityCommentRepository.save(communityComment), user_id);

            System.out.println("저장이 되었나?: " + result.getCommunity_comment_content());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ModifyRes deleteComment(Long community_comment_id) throws Exception {
        ModifyRes modifyRes = new ModifyRes();

        try {
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
    public CommunityCommentRes modifyComment(CommunityCommentModiReq communityCommentModiReq) throws Exception {
        CommunityCommentRes tmp = new CommunityCommentRes();
        try {
            CommunityComment communityComment = communityCommentRepository.findOneById(communityCommentModiReq.getCommunity_comment_id());

            if(communityComment.equals(new CommunityComment())) return tmp;
            int result = communityCommentRepository.modifyComment(
                    communityCommentModiReq.getCommunity_comment_content(),
                    communityCommentModiReq.getCommunity_comment_id()
            );

            if(result ==1) return new CommunityCommentRes(communityComment, communityCommentModiReq);
            return tmp;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tmp;
    }


    @Override
    public PageRes findAll(CommentCheckReq commentCheckReq) throws Exception {
        PageRes pageRes = new PageRes();
        Long comment_id = commentCheckReq.getCommunity_comment_id();
        Long user_id = commentCheckReq.getUser_id();

        try {
            List<CommunityComment> comments = communityCommentRepository.findAll(comment_id);
            if(comments.isEmpty()) return pageRes;

            pageRes.setContents(comments.stream()
                    .map(comment -> new CommunityCommentRes(comment, user_id))
                    .collect(Collectors.toList())
            );
        }catch (Exception e){
            e.printStackTrace();
        }
        return pageRes;
    }

    @Override
    public CommunityCommentRes findById(CommentCheckReq commentCheckReq) throws Exception{
        CommunityComment communityComment;
        CommunityCommentRes communityCommentRes = new CommunityCommentRes();
        Long comment_id = commentCheckReq.getCommunity_comment_id();
        Long user_id = commentCheckReq.getUser_id();

        try {
            communityComment = communityCommentRepository.findOneById(comment_id);
            communityCommentRes = new CommunityCommentRes(communityComment, user_id);

            return communityCommentRes;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return communityCommentRes;
    }

}
