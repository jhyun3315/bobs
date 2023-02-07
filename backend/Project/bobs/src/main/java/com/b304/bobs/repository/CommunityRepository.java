package com.b304.bobs.repository;

import com.b304.bobs.dto.CommunityDTO;
import com.b304.bobs.entity.Community;
import com.b304.bobs.entity.CommunityComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE community SET community_title =:communityTitle, community_content =:communityContent, community_img =:communityImg WHERE community_id =:communityId AND community_deleted =0", nativeQuery = true)
    public int modifyCommunity(@Param("communityId") Long community_id, @Param("communityTitle") String community_title, @Param("communityContent") String community_content, @Param("communityImg") String community_img);

    @Modifying
    @Transactional
    @Query(value = "UPDATE community SET community_deleted = 1 WHERE community_id =:communityId AND community_deleted=0", nativeQuery = true)
    public int deleteCommunityById(@Param("communityId") Long community_id);

    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM community WHERE community_id =:communityId AND community_deleted = 0", nativeQuery = true)
    public Community findOneById(@Param("communityId")Long community_id);

    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM community WHERE user_id =:userId AND community_deleted = 0 ORDER BY community_created DESC", nativeQuery = true)
    public Page<Community> findByUser(@Param("userId") Long user_id, @PageableDefault(size = 20 )Pageable pageable);

    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM community WHERE community_deleted = 0 ORDER BY community_created DESC", nativeQuery = true)
    public Page<Community> findAll(@PageableDefault(size = 20) Pageable pageable);

}
