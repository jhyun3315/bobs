package com.b304.bobs.repository;

import com.b304.bobs.entity.CommunityComment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommunityCommentRepository {

    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM community_comment WHERE community_id =:communityId AND community_comment.community_comment_deleted = 0")
    public List<CommunityComment> findComment(@Param("communityId") Long community_id);
}
