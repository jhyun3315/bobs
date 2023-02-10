package com.b304.bobs.api.service;

import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.response.StudyCommentRes;
import com.b304.bobs.db.entity.StudyComment;
import com.b304.bobs.db.repository.StudyCommentRepository;
import com.b304.bobs.db.repository.StudyRepository;
import com.b304.bobs.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudyCommentServiceImpl implements StudyCommentService {

    final private StudyCommentRepository studyCommentRepository;
    final private UserRepository userRepository;
    final private StudyRepository studyRepository;

    @Override
    public StudyCommentRes createComment(StudyCommentRes studyCommentRes) throws Exception {
        StudyComment studyComment = new StudyComment();
        StudyCommentRes result = new StudyCommentRes();

        try {
            studyComment.setStudy_comment_content(studyCommentRes.getStudy_comment_content());
            studyComment.setStudy_comment_deleted(false);
            studyComment.setStudy_comment_created(LocalDateTime.now());

            if (userRepository.findById(studyCommentRes.getUser_id()).isPresent()) {
                studyComment.setUser(userRepository.findById(studyCommentRes.getUser_id()).orElse(null));

                if (studyRepository.findById(studyCommentRes.getStudy_id()).isPresent()) {
                    studyComment.setStudy(studyRepository.findById(studyCommentRes.getStudy_id()).orElse(null));
                    result = new StudyCommentRes(studyCommentRepository.save(studyComment));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public ModifyRes deleteComment(Long study_comment_id) throws Exception {
        ModifyRes modifyRes = new ModifyRes();

        try {
            System.out.println(study_comment_id);
            int result = studyCommentRepository.deleteComment(study_comment_id);
            modifyRes.setResult(result);
            modifyRes.setId(study_comment_id);
            return modifyRes;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return modifyRes;
    }

    @Override
    public ModifyRes modifyComment(StudyCommentRes studyCommentRes) throws Exception {
        ModifyRes modifyRes = new ModifyRes();

        try {
            int result = studyCommentRepository.modifyComment(
                    studyCommentRes.getStudy_comment_content(),
                    studyCommentRes.getStudy_comment_id()
            );

            modifyRes.setResult(result);
            modifyRes.setId(studyCommentRes.getStudy_id());
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
            List<StudyComment> comments = studyCommentRepository.findAll(comment_id);
            if (comments.isEmpty()) return pageRes;

            pageRes
                    .setContents(comments.stream()
                            .map(StudyCommentRes::new)
                            .collect(Collectors.toList())
                    );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pageRes;
    }
}
