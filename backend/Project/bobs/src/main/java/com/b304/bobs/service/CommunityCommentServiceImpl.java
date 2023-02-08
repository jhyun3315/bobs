package com.b304.bobs.service;

import com.b304.bobs.dto.CommunityCommentDTO;
import com.b304.bobs.dto.CommunityDTO;
import com.b304.bobs.dto.ModifyDTO;
import com.b304.bobs.dto.PageResultDTO;
import com.b304.bobs.entity.Community;
import com.b304.bobs.entity.CommunityComment;
import com.b304.bobs.repository.CommunityCommentRepository;
import com.b304.bobs.repository.CommunityRepository;
import com.b304.bobs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityCommentServiceImpl implements CommunityCommentService{

    final private CommunityCommentRepository communityCommentRepository;
    final private UserRepository userRepository;
    final private CommunityRepository communityRepository;

    @Override
    public CommunityCommentDTO createComment(CommunityCommentDTO communityCommentDTO) throws Exception {
        CommunityComment communityComment = new CommunityComment();
        CommunityCommentDTO result = new CommunityCommentDTO();

        try  {
            communityComment.setCommunity_comment_content(communityCommentDTO.getCommunity_comment_content());
            communityComment.setCommunity_comment_deleted(false);
            communityComment.setCommunity_comment_created(LocalDateTime.now());

            if(userRepository.findById(communityCommentDTO.getUser_id()).isPresent()){
                communityComment.setUser(userRepository.findById(communityCommentDTO.getUser_id()).orElse(null));

                if(communityRepository.findById(communityCommentDTO.getCommunity_id()).isPresent()){
                    communityComment.setCommunity(communityRepository.findById(communityCommentDTO.getCommunity_id()).orElse(null));
                    result = new CommunityCommentDTO(communityCommentRepository.save(communityComment));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public CommunityCommentDTO deleteComment(CommunityCommentDTO communityCommentDTO) throws Exception {
        return null;
    }

    @Override
    public ModifyDTO modifyComment(CommunityCommentDTO communityCommentDTO) throws Exception {
        ModifyDTO modifyDTO = new ModifyDTO();

        try {
            int result = communityCommentRepository.modifyComment(
                    communityCommentDTO.getCommunity_comment_content(),
                    communityCommentDTO.getCommunity_comment_id()
            );

            System.out.println(communityCommentDTO.getCommunity_comment_id());
            System.out.println(result);

            modifyDTO.setResult(result);
            modifyDTO.setId(communityCommentDTO.getCommunity_id());
            return modifyDTO;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return modifyDTO;
    }


    @Override
    public PageResultDTO findAll(Long comment_id, Pageable pageable) throws Exception {
        PageResultDTO pageResultDTO = new PageResultDTO();

        try {
            Page<CommunityComment> comments = communityCommentRepository.findAll(comment_id, pageable);
            if(comments.isEmpty()) return pageResultDTO;

            pageResultDTO
                    .setContents(comments.stream()
                            .map(CommunityCommentDTO::new)
                            .collect(Collectors.toList())
                    );
            pageResultDTO.setTotalPages(comments.getTotalPages());
        }catch (Exception e){
            e.printStackTrace();
        }
        return pageResultDTO;
    }

}
