package com.b304.bobs.db.repository;

import com.b304.bobs.db.entity.StudyComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudyCommentRepository extends JpaRepository<StudyComment, Long> {

    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM study_comment WHERE study_id =:studyId AND study_comment_deleted = 0", nativeQuery = true)
    List<StudyComment> findAll(@Param("studyId") Long study_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE study_comment SET study_comment_deleted = 1 WHERE study_comment_id =:studyCommentId AND study_comment_deleted =0", nativeQuery = true)
    int deleteComment(@Param("studyCommentId") Long study_comment_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE study_comment SET study_comment_content =:studyContent WHERE study_comment_id =:commentId AND study_comment_deleted =0", nativeQuery = true)
    int modifyComment( @Param("studyContent") String study_content, @Param("commentId") Long study_id);

}
