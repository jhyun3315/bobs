package com.b304.bobs.repository;

import com.b304.bobs.entity.CommunityComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommunityCommentRepository extends JpaRepository<CommunityComment,Long> {

    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM community_comment WHERE community_id =:communityId AND community_comment_deleted = 0", nativeQuery = true)
    List<CommunityComment> findAll(@Param("communityId") Long community_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE community_comment SET community_comment_deleted = 1 WHERE community_comment_id =:communityCommentId AND community_comment_deleted =0", nativeQuery = true)
    int deleteComment(@Param("communityCommentId") Long community_comment_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE community_comment SET community_comment_content =:commentContent WHERE community_comment_id =:commentId AND community_comment_deleted =0", nativeQuery = true)
    int modifyComment( @Param("commentContent") String comment_content, @Param("commentId") Long community_id);

}
