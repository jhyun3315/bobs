package com.b304.bobs.repository;

import com.b304.bobs.entity.Community;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community, String> {

    public Community findOneBiyId(Long community_id);

    @Query(value = "SELECT * FROM Community WHERE user_id =: userId ORDER BY community_created DESC", nativeQuery = true)
    public Page<Community>  findAllById(@Param("user_id") Long user_id, Pageable pageable);

    @Query(value = "SELECT * from Community", nativeQuery = true)
    public Page<Community> findAll(Pageable pageable);




//    public void write(Community community){
//        em.persist(community);
//    }
//
//    public void commentWrite(){
//
//    }
//
//    @Transactional(readOnly = true)
//    public List<Community> findAllById(Long user_id){
//        return em.createQuery("select c from Community c where m.user_id =: userId" , Community.class)
//                .setParameter("userId", user_id)
//                .getResultList();
//
//    }
//
//    @Transactional(readOnly = true)
//    public List<Community> findAll(){
//        return em.createQuery("select c from Community as c", Community.class)
//                .getResultList();
//    }
}
