package com.b304.bobs.db.repository;

import com.b304.bobs.db.entity.Study;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudyRepository extends JpaRepository<Study, Long> {

    // 방장이 스터디 lock / unlock
    @Modifying
    @Transactional
    @Query(value = "UPDATE study SET study_lock = if(study_lock = 0, 1, 0) where study_id = :studyId", nativeQuery = true)
    int lockStudy(@Param("studyId") Long study_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE study SET study_title =:studyTitle, study_content =:studyContent, study_time =:studyTime WHERE study_id =:studyId AND study_deleted =0", nativeQuery = true)
    int modifyStudy(@Param("studyId") Long study_id, @Param("studyTitle") String study_title, @Param("studyContent") String study_content, @Param("studyTime") String studyTime);

    @Modifying
    @Transactional()
    @Query (value = "UPDATE study SET study_deleted = 1 WHERE study_id =:studyId AND study_deleted=0", nativeQuery = true)
    int deleteStudyById(@Param("studyId") Long study_id);

    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM study WHERE study_id =:studyId AND study_deleted = 0", nativeQuery = true)
    Study findOneById(@Param("studyId")Long study_id);

    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM study WHERE study_deleted = 0 ORDER BY study_created DESC", nativeQuery = true)
    Page<Study> findAll(@PageableDefault(size = 20) Pageable pageable);

}
