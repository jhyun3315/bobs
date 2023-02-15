package com.b304.bobs.db.repository;

import com.b304.bobs.db.entity.CommunityComment;
import com.b304.bobs.db.entity.RecipeLike;
import com.b304.bobs.db.entity.Refrige;
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

    @Query(value =
            "select c from CommunityComment c " +
                    "left join fetch c.user u " +
                    "where c.community.community_id =:communityId AND c.community_comment_deleted = false " +
                    "order by c.community_comment_created ASC", countQuery = "select count(*) from CommunityComment c where c.community.community_id =: communityId AND c.community_comment_deleted = false")
    List<CommunityComment> findAll(@Param("communityId") Long community_id);

    @Modifying
    @Query(value = "UPDATE community_comment SET community_comment_deleted = 1 WHERE community_comment_id =:communityCommentId AND community_comment_deleted =0", nativeQuery = true)
    int deleteComment(@Param("communityCommentId") Long community_comment_id);

    @Modifying
    @Query(value = "UPDATE community_comment SET community_comment_content =:commentContent WHERE community_comment_id =:commentId AND community_comment_deleted =0", nativeQuery = true)
    int modifyComment( @Param("commentContent") String comment_content, @Param("commentId") Long community_id);

    @Query(value ="SELECT * FROM community_comment WHERE community_comment_id =:comment_id AND community_comment_deleted =0", nativeQuery = true)
    CommunityComment findOneById(@Param("comment_id") Long community_comment_id);

    @Modifying(clearAutomatically = true)
    @Query("update CommunityComment set community_comment_deleted =1 where community.community_id =:community_id")
    int deleteCommunityCommentsById(@Param("community_id") Long community_id);

}
