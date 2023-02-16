package com.b304.bobs.db.repository;

import com.b304.bobs.db.entity.StudyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudyMemberRepository extends JpaRepository<StudyMember, Long> {

    // 팀원수 조회
    @Query(value = "SELECT count(*) FROM study_member WHERE study_id =:studyId AND study_member_deleted =0", nativeQuery = true)
    Long countMember(@Param("studyId")Long study_id);

    // 탈퇴 (구성원)
    @Modifying
    @Query(value = "UPDATE study_member SET study_member_deleted = 1 WHERE study_id =:studyId AND user_id =:userId AND study_member_deleted =0", nativeQuery = true)
    int deleteStudyMember(@Param("studyId") Long study_id, @Param("userId") Long user_id);

    // 스터디 아이디로 모든 멤버 가져오기
    @Query(value ="SELECT * FROM study_member WHERE study_id =:studyId AND study_member_deleted=0",nativeQuery = true)
    List<StudyMember> findAllbyStudyId(@Param("studyId") Long study_id);

    // 사용자가 가입한 스터디 정보 가져오기
    @Query(value="SELECT * FROM study_member WHERE user_id =:userId AND study_member_deleted=0 ",nativeQuery = true)
    List<StudyMember> findByUserId(@Param("userId") Long user_id);

    // 스터디에 가입한 모든 멤버 가져오기
    @Query(value="SELECT * FROM study_member WHERE study_id =:studyId And study_member_deleted=0", nativeQuery = true)
    List<StudyMember> findOneByStudyId(@Param("studyId") Long study_id);

    // 사용자가 해당 스터디의 구성원인지 확인하기
    @Query(value = "SELECT * FROM study_member WHERE study_id =:studyId AND user_id =:userId AND study_member_deleted =0", nativeQuery = true)
    Optional<StudyMember> checkStudyMember(@Param("studyId") Long study_id, @Param("userId") Long user_id);

    //사용자가 가입한 스터디 가져오기
    @Query(value = "SELECT * FROM study_member WHERE study_id =:studyId AND user_id =:userId", nativeQuery = true)
    StudyMember findOne(@Param("studyId") Long study_id, @Param("userId") Long user_id);

}