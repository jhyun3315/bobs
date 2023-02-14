package com.b304.bobs.db.repository;

import com.b304.bobs.db.entity.StudyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudyMemberRepository extends JpaRepository<StudyMember, Long> {

    // 팀원수 조회
    @Query(value = "SELECT count(*) FROM study_member WHERE study_id =:studyId AND study_member_deleted =0", nativeQuery = true)
    Long countMember(@Param("studyId")Long study_id);


    // 탈퇴 (구성원)
    @Modifying
    @Transactional
    @Query(value = "UPDATE study_member SET study_member_deleted = 1 WHERE study_member_id =:studyMemberId AND study_member_deleted =0", nativeQuery = true)
    int deleteStudyMember(@Param("studyMemberId") Long study_member_id);
}